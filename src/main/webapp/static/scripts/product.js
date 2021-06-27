// DELETE
const deleteButtons = document.querySelectorAll('.delete-button');
deleteButtons.forEach((button) => {
  button.onclick = (e) => {
    if (
      confirm(
        `Are you sure want to delete product with Product ID = ${button.id}`
      )
    ) {
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

// UPDATE
const updateButtons = document.querySelectorAll('.update-button');
deleteButtons.forEach((button) => {
  button.onclick = (e) => {
    fetch('product', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: `productActivity=updateProduct&productId=${button.id}`,
    });
  };
});
