package com.springmvc.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;
import com.springmvc.util.Util;

@Service
public class BookServiceImpl implements BookService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookRepository bookRepository;

	public BookServiceImpl() {}

	@Override
	public List<Book> getAllBookList() {
		return bookRepository.getAllBookList();
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		return bookRepository.getBookListByCategory(category);
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		return bookRepository.getBookListByFilter(filter);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookRepository.getBookById(bookId);
	}

	@Override
	public void setNewBook(Book book) {
		MultipartFile bookImage = book.getBookImage();
		if (bookImage != null && !bookImage.isEmpty()) {
			try {
				File savePath = new File("/Users/jamesj/spring_upload_test/" + book.getBookId());
				if (!savePath.exists()) {
					if (savePath.mkdirs()) {
						log.info("{}New save path is created. (path={})", Util.getLogPrefix(getClass()), savePath.getAbsolutePath());
					}
				}
				File saveFile = new File(savePath.getAbsolutePath() + "/" + book.getBookId() + ".png");

				bookImage.transferTo(saveFile);
			} catch (Exception e) {
				log.warn("{}", Util.getLogPrefix(getClass()), e);
				return;
			}
		}

		bookRepository.setNewBook(book);
	}
}
