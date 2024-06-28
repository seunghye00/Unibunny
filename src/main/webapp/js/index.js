let sin = Math.sin;
let cos = Math.cos;
let PI = Math.PI;
let fov = 150;

class Dot {
  constructor(x, y, z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.image = new Image();
    this.image.src = '../image/index/cirbyStar.png';
    // 여기에 사용할 이미지의 경로를 입력하세요
  }
}

let canvas;
let context;
let dots = [];
let dotsLength = (innerWidth + innerHeight) / 10; // 별의 개수를 두 배로 증가

function setSize() {
  canvas.width = innerWidth;
  canvas.height = innerHeight;
  initDots();
  context.fillStyle = '#ffffff';
  if (innerWidth < 800) {
    context.globalAlpha = 0.3;
  } else {
    context.globalAlpha = 0.8;
  }
}

function initDots() {
  dots = [];
  dotsLength = (innerWidth + innerHeight) / 10; // 별의 개수를 두 배로 증가
  let x, y, z;
  for (let i = 0; i < dotsLength; i++) {
    x = (Math.random() * 2 - 1) * innerWidth; // -innerWidth에서 innerWidth로 변경
    y = (Math.random() * 2 - 1) * innerHeight; // -innerHeight에서 innerHeight로 변경
    z = (Math.random() * 2 - 1) * innerWidth; // -innerWidth에서 innerWidth로 변경
    dots.push(new Dot(x, y, z));
  }
}

function render() {
  context.clearRect(0, 0, canvas.width, canvas.height);
  let dot;
  for (let i = 0; i < dots.length; i++) {
    dot = dots[i];
    dot.z -= 4;
    if (dot.z < -fov) {
      dot.z += (innerWidth + innerHeight) / 2;
    }
    drawDotImage(dot);
  }
  requestAnimationFrame(render);
}

function drawDotImage(dot) {
  let scale, x2d, y2d;
  scale = fov / (fov + dot.z);
  x2d = dot.x * scale + innerWidth / 2;
  y2d = dot.y * scale + innerHeight / 2;
  let imageWidth = scale * 15; // 이미지의 가로 크기 조절
  let imageHeight = scale * 15; // 이미지의 세로 크기 조절
  context.drawImage(dot.image, x2d, y2d, imageWidth, imageHeight);
}

function init() {
  canvas = document.querySelector('.canvas');
  context = canvas.getContext('2d');
  setSize();
  render();

  let images = document.querySelectorAll('.index_wrap .img_box a img');
  images.forEach((img, index) => {
    setTimeout(() => {
      // 이미지의 opacity를 1로 변경
      img.style.opacity = 1;
      // 이미지의 위치를 원래대로 변경
      img.style.transform = 'translateX(0)';
      // 1초 간격으로 등장하게 설정
    }, 300 * (index + 1));
    // 마우스 오버 이벤트 추가
    img.addEventListener('mouseover', () => {
      img.style.transform = 'scale(1.2)';
    });

    // 마우스 아웃 이벤트 추가
    img.addEventListener('mouseout', () => {
      img.style.transform = 'translateX(0)';
    });
  });
}

addEventListener('resize', setSize);
init();
