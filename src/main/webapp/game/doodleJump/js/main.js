var isStart = false,
    music = [],
    initScreen = function(callback) { // HTML 폰트 크기 초기화
        document.getElementById("html").style.setProperty("font-size", gameWidth / 375 * 312.5 + "%");
    };

function setSize() {
    gameWidth = window.innerWidth > 750 ? 750 : window.innerWidth;
    gameHeight = window.innerHeight;
    gameScale = gameWidth / 750;
};

function winOrientation() {
    if (window.innerWidth > window.innerHeight && navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
        console.log('가로 화면');
        if (isStart) {
            game.paused = true;
        };
        document.getElementById("forhorview").style.setProperty("display", "flex");
        document.getElementById("forhorview").innerHTML = '휴대폰을 세로로 돌려주세요~~';
    } else {
        console.log('세로 화면');
        if (!isStart) {
            document.getElementById("forhorview").innerHTML = '설정 파일을 로드 중입니다...';
            loadJS(function() {
                game.state.add('Load', Load);
                game.state.add('Game', MyGame);
                game.state.start('Load');
                document.getElementById("forhorview").style.setProperty("display", "none");
            });
            isStart = true;
        } else {
            game.paused = false;
            document.getElementById("forhorview").style.setProperty("display", "none");
        };
    };
};

function loadJS(fun) {
    var a = document.createElement('script');
    a.src = "phaser.min.js";
    document.head.appendChild(a);
    a.onload = function() {
        var b = document.createElement('script');
        b.src = "js/load.js";
        document.head.appendChild(b);
        b.onload = function() {
            var c = document.createElement('script');
            c.src = "js/game.js";
            document.head.appendChild(c);
            c.onload = function() {
                fun();
            };
        };
    };
};

// 랜덤 숫자
function l(a, b) {
    a = Math.round(a);
    b = Math.round(b);
    return Math.round((b - a) * Math.random()) + a;
}
function ranArr(a, b) {
    return Math.random() > .5 ? -1 : 1;
};

window.onload = function() {
    setSize();
    initScreen();
    winOrientation();
};

window.onresize = function() {
    setSize();
    initScreen();
    winOrientation();
};
