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
addCartButtons.forEach((button) => {
  button.onclick = (e) => {
    // button.setAttribute('name', 'addCart');

    alert('Product added to cart');
  };
});

const buyButtons = document.querySelectorAll('#buy');
buyButtons.forEach((button) => {
  button.onclick = (e) => {
    // button.setAttribute('name', 'addCart');
    // location.href = 'cart';
  };
});

function addToCart(productId) {
  fetch('cart', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: `productId=${productId}`,
  });

  return false;
}
