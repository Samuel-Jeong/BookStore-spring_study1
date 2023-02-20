package com.springmvc.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;
import com.springmvc.exception.CategoryException;
import com.springmvc.service.BookService;
import com.springmvc.validator.BookValidator;

@Controller
//@RequestMapping(value="/books", method=RequestMethod.GET)
@RequestMapping("/books") // GET 생략 가능
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookValidator bookValidator;

	/**
	 * 폼 페이지에서 전송되는 요청 파라미터의 데이터 바인딩을 사용자가 직접 정의
	 * > 폼 파라미터 이름을 제한하거나 허용할 수 있다.
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(
					"bookId", "name", "unitPrice", "author", "description",
					"publisher", "category", "unitsInStock", "totalPages",
					"releaseDate", "condition", "bookImage"
				);
		binder.setValidator(bookValidator);
	}

	// http://localhost:8080/BookMarket/books
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> bookList = bookService.getAllBookList();
		model.addAttribute("bookList", bookList);
		return "books";
	}

//	@GetMapping("/all")
//	public String requestAllBookList(Model model) {
//		List<Book> bookList = bookService.getAllBookList();
//		model.addAttribute("bookList", bookList);
//		return "books";
//	}

	// http://localhost:8080/BookMarket/books/all
	@GetMapping("/all")
	public ModelAndView requestAllBooks() {
		ModelAndView modelAndView = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		modelAndView.addObject("bookList", list);
		modelAndView.setViewName("books");
		return modelAndView;
	}

	// http://localhost:8080/BookMarket/books/IT전문서
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory,
											Model model) {
		List<Book> bookListByCategory = bookService.getBookListByCategory(bookCategory);
		if (bookListByCategory == null || bookListByCategory.isEmpty()) {
			throw new CategoryException();
		}

		model.addAttribute("bookList", bookListByCategory);
		return "books";
	}

	// http://localhost:8080/BookMarket/books/filter/bookFilter;publisher=길벗;category=IT전문서
	// http://localhost:8080/BookMarket/books/filter/bookFilter;publisher=길벗;category=IT전문서,IT활용서
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(
			@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter,
			Model model) {
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}

	// http://localhost:8080/BookMarket/books/book?id=ISBN1235
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "book";
	}

	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		return "addBook";
	}

	@PostMapping("/add")
	public String submitAddNewBook(@Valid @ModelAttribute("NewBook") Book book,
									BindingResult result) {

		// 유효성 검사로 발생된 오류가 있으면 뷰 이름 'addBook' 을 반환하여 addBook.jsp 에 출력하도록 한다.
		if (result.hasErrors()) {
			return "addBook";
		}

		bookService.setNewBook(book);
		return "redirect:/books";
	}

	/**
	 * 클라이언트가 뷰를 요청할 때 뷰에서 사용할 값을 정의해준다.
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("addTitle", "신규 도서 등록");
	}

	// BookIdException 처리에 대해서 Controller 에서 처리하고 있으므로 이 handleError 함수에서 처리되고,
	// ControllerAdivce 가 정의된 CommonException 에서 처리되지 않는다.
	@ExceptionHandler(value={BookIdException.class})
	public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invalidBookId", exception.getBookId());
		modelAndView.addObject("exception", exception);
		modelAndView.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		modelAndView.setViewName("errorBook");
		return modelAndView;
	}

}
