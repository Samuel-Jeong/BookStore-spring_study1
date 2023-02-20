package com.springmvc.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;

@Primary
@Repository
public class BookRepositoryImpl implements BookRepository {

	private final String SELECT_ALL_BOOK_TABLE = "select * from book";
	private final String COUNT_BY_BOOKID = "select count(*) from book where b_bookId=?";
	private final String SELECT_BY_BOOKID = "select * from book where b_bookId=?";
	private final String SELECT_BY_CATEGORY = "select * from book where b_category like '%?%'";
	private final String SELECT_BY_PUBLISHER = "select * from book where b_publisher like '%?%'";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Book> getAllBookList() {
		return jdbcTemplate.query(SELECT_ALL_BOOK_TABLE, new BookRowMapper());
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		return jdbcTemplate.query(SELECT_BY_CATEGORY, new Object[] { category }, new BookRowMapper());
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<>();
		Set<Book> booksByCategory = new HashSet<>();

		Set<String> booksByFilter = filter.keySet();

		if (booksByFilter.contains("publisher")) {
			for (String publisherName : filter.get("publisher")) {
				booksByPublisher.addAll(
					jdbcTemplate.query(SELECT_BY_PUBLISHER, new Object[] { publisherName }, new BookRowMapper())
				);
			}
		}

		if (booksByFilter.contains("category")) {
			for (String category : filter.get("category")) {
				booksByCategory.addAll(
					jdbcTemplate.query(SELECT_BY_CATEGORY, new Object[] { category }, new BookRowMapper())
				);
			}
		}

		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		Book bookInfo = null;

		int rowCount = jdbcTemplate.queryForObject(COUNT_BY_BOOKID, Integer.class, bookId);
		if (rowCount != 0) {
			bookInfo = jdbcTemplate.queryForObject(SELECT_BY_BOOKID, new Object[] { bookId }, new BookRowMapper());
		}

		if (bookInfo == null) {
			throw new BookIdException(bookId);
		}

		return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
		// TODO Auto-generated method stub

	}

}
