// let logoutButton = document.querySelector('#logout');
// logoutButton.addEventListener('click', () => {
//     let logout = confirm('You will be ');
//     if (logout) {
//         window.location.href = '/';
//     }
// });

const searchBar = document.querySelector('#search-bar');
let products = document.querySelectorAll('.product');
searchBar.oninput = (e) => {
	let searchInput = searchBar.value.toLowerCase();

	products.forEach((product) => {
		let productName = product.textContent.toLowerCase();

		// Hide if not same
		if (!productName.includes(searchInput)) {
			product.style.display = 'none';
		} else {
			product.style.display = 'grid';
		}
	});
};

const addCartButtons = document.querySelectorAll('#add-cart');
addCartButtons.forEach(button => {
	button.onclick = (e) => {
		e.preventDefault();
		alert('Add to cart');
	}
});