package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.domain.Order;
import com.springmvc.exception.BookIdException;
import com.springmvc.repository.BookRepository;
import com.springmvc.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	@Override
	public void confirmOrder(String bookId, long quantity) {
		Book book = bookRepository.getBookById(bookId);
		if (book == null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}
		if (book.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException(
					String.format("품절입니다. 사용가능한 재고 수 : [%]", book.getUnitsInStock())
				);
		}
		book.setUnitsInStock(book.getUnitsInStock() - quantity);
	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}


}
