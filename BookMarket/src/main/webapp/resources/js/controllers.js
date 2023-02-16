function addToCart(action) {
	document.addForm.action = action;
	document.addForm.submit();
	alert("도서가 장바구니에 추가되었습니다");
}

function removeFromCart(action) {
	document.removeForm.action = action;
	document.removeForm.submit();
	// reload 코드가 있으면 바로 위에 submit 코드가 실행되지 않는 오류 발생...
	//window.location.reload();
	alert("도서가 장바구니에서 삭제되었습니다");
}

function clearCart() {
	document.clearForm.submit();
	// reload 코드가 있으면 바로 위에 submit 코드가 실행되지 않는 오류 발생...
	//window.location.reload();
}