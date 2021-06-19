let logoutButton = document.querySelector('#logout');
logoutButton.addEventListener('click', () => {
    let logout = confirm('You will be ');
    if (logout) {
        window.location.href = '/';
    }
});