const colors = [
  '#83c5be',
  '#ffc8dd',
  '#d8f3dc',
  'rgb(219, 227, 233)',
  '#edc4b3',
];
let counter = 0;
let body = document.querySelector('body');

let interval = setInterval(() => {
  if (counter === colors.length) {
    counter = 0;
  }
  body.style.backgroundColor = colors[counter];
  counter++;
}, 3000);