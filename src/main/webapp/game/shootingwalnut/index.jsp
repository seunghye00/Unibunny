<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top-Down Shooter Game</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/phaser/3.80.1/phaser.min.js"></script>
    <script src="../shootingwalnut/js/controller.js"></script>
    <script src="../shootingwalnut/js/gameover.js"></script>
    <script src="../shootingwalnut/js/gamestart.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet"> <!-- Google Fonts 추가 -->
    <script src="../js/common.js"></script>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: 'Press Start 2P', cursive; /* 기본 폰트 설정 */
        }

        #game-container {
            background-color: #ffffff;
            width: 500px;
            height: 500px;
            border: 1px solid black;
            margin: auto;
        }
    </style>
</head>

<body>
    <div id="game-container"></div>

    <script>
        let config = {
            type: Phaser.AUTO,
            parent: "game-container",
            width: 500,
            height: 500,
            physics: {
                default: "arcade",
                arcade: {
                    gravity: { y: 0 },
                    debug: false
                }
            },
            scene: [gamestart, Controller, gameover] // 초기 씬을 gamestart로 설정
        };

        let game = new Phaser.Game(config);
    </script>
</body>

</html>
