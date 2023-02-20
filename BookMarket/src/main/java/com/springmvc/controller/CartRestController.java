package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springmvc.domain.Book;
import com.springmvc.domain.Cart;
import com.springmvc.domain.CartItem;
import com.springmvc.exception.BookIdException;
import com.springmvc.service.BookService;
import com.springmvc.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private BookService bookService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.setAllowedFields(
//				);
	}

	// 웹 요청 URL 이 http://localhost:8080/BookMarket/cart/ 일 때, 요청 처리 메서드로 사용자 요청을 처리
	// 세션 ID 값을 가져와서 URI cart/sessionId 로 리다이렉션
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		return "redirect:/cart/" + sessionId;
	}

	// Cart 클래스 정보를 HTTP 요청 body 로 전달받아 장바구니를 새로 생성하고 HTTP 응답 body 로 다시 그대로 전달
	@PostMapping
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return cartService.create(cart);
	}

	// http://localhost:8080/BookMarket/cart/{cartId}
	@GetMapping("{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId,
									Model model) {
		Cart cart = cartService.read(cartId);
		model.addAttribute("cart", cart);
		return "cart"; // cart.jsp
	}

	@PutMapping("{cartId}")
	public @ResponseBody Cart read(@PathVariable(value="cartId") String cartId) {
		return cartService.read(cartId);
	}

	@PutMapping("/add/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT) // 오류 응답 상태 코드 설정
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();

		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}

		Book book = bookService.getBookById(bookId);
		if (book == null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}

		cart.addCartItem(new CartItem(book));
		cartService.update(sessionId, cart);
	}

	@PutMapping("/remove/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();

		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(new Cart(bookId));
		}

		Book book = bookService.getBookById(bookId);
		if (book == null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}

		cart.removeCartItem(new CartItem(book));
		cartService.update(sessionId, cart);
	}

	@DeleteMapping("/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable String cartId) {
		cartService.delete(cartId);
	}

}
