// DELETE
const deleteButtons = document.querySelectorAll('.delete-button');
deleteButtons.forEach((button) => {
  button.onclick = (e) => {
    const string = `Are you sure want to delete ${button.dataset.productName}`;
    if (confirm(string)) {
      fetch('product', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `productActivity=deleteProduct&productId=${button.id}`,
      }).then((res) => {
        location.reload();
      });
    } else {
      return;
    }
  };
});
