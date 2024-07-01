class gameover extends Phaser.Scene {
    constructor() {
        super({ key: "gameover" });
    }

    init(data) {
        this.score = data.score;
        this.gameId = data.gameId;
    }

    preload() {
        // 필요한 자원 로딩
    }

    create() {
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 100, "GAME OVER", {
            fontSize: "50px",
            fill: "#FF0000",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5);

        // 전달된 점수 표시
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 20, `Score: ${this.score}`, {
            fontSize: "35px",
            fill: "#FFFFFF",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5);

        let restartButton = this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 + 50, "RESTART", {
            fontSize: "25px",
            fill: "#FF0000",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5).setInteractive();

        restartButton.on("pointerover", () => {
            restartButton.setStyle({ backgroundColor: "#0000FF" });
            this.game.canvas.style.cursor = "pointer";
        });

        restartButton.on("pointerout", () => {
            restartButton.setStyle({ backgroundColor: null });
            this.game.canvas.style.cursor = "default";
        });

        restartButton.on("pointerdown", () => {
            this.scene.start("controller");
        });

        // 게임 오버 로그 전송
        EndGameFunctions.sendScore(this.score, this.gameId);
    }

    update() {
        // 필요시 업데이트 로직 추가
    }
}

var EndGameFunctions = {
    sendScore: function(score, gameId) {
        console.log('Sending score:', score, 'for game ID:', gameId, 'with log sequence:', game.logSeq);
        $.ajax({
            url: '/submit.score',
            type: 'POST',
            data: {
                score: score,
                gameID: gameId,
                log_seq: game.logSeq // game 객체의 logSeq 속성 사용
            },
            success: function(response) {
                console.log('Score submission successful:', response); // 성공 로그 추가
            },
            error: function(error) {
                console.error('Error during score submission:', error); // 실패 로그 추가
            }
        }).done(function(resp) {
            console.log('sendScore AJAX request done.');
        });
    }
};
