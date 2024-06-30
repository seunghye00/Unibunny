class gameover extends Phaser.Scene {
    constructor() {
        super({ key: "gameover" });
      	EndGameFunctions.sendScore(score,gameId)
    }

    preload() {
        // 필요한 자원 로딩
    }

    create(data) { // 데이터 파라미터 추가
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 100, "GAME OVER", {
            fontSize: "50px",
            fill: "#FF0000",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5);

        // 전달된 점수 표시
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 20, `Score: ${data.score}`, {
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
    }

    update() {
        // 필요시 업데이트 로직 추가
    }
}
