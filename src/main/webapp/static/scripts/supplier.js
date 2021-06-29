// DELETE
const deleteButtons = document.querySelectorAll('.delete-button');
deleteButtons.forEach((button) => {
  button.onclick = (e) => {
    const string = `Are you sure want to delete supplier with Supplier ID = ${button.id}`;
    if (confirm(string)) {
      fetch('supplier', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `supplierActivity=deleteSupplier&supplierId=${button.id}`,
      }).then((res) => {
        location.reload();
      });
    } else {
      return;
    }
  };
});
