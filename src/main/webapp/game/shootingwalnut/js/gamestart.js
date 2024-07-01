class gamestart extends Phaser.Scene {

    constructor() {
        super({ key: "gamestart" });
    }

    preload() {
        this.load.image('background', 'img/background.jpg'); // 배경 이미지 로드
        this.load.image('player', 'img/player.png'); // 플레이어 이미지 로드
    }

    create() {
        // 배경 이미지 추가
        this.background = this.add.image(0, 0, 'background').setOrigin(0, 0);
        this.background.displayWidth = this.cameras.main.width;
        this.background.displayHeight = this.cameras.main.height;

        // 투명도를 적용한 블랙 화면 추가
        let blackOverlay = this.add.graphics();
        blackOverlay.fillStyle(0x000000, 0.5); // 블랙 색상, 50% 투명도
        blackOverlay.fillRect(0, 0, this.cameras.main.width, this.cameras.main.height);

        // 플레이어 이미지 추가
        this.player = this.add.image(this.cameras.main.width / 2, this.cameras.main.height - 80, 'player'); // 적절한 위치 설정
        this.player.setScale(0.3); // 플레이어 이미지 크기 조정

        // 게임 시작 텍스트 추가
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 75, "GAME START!", {
            fontSize: "40px",
            fill: "#FF0000",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5);

        // 시작 버튼 추가
        let startButton = this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 + 50, "START", {
            fontSize: "30px",
            fill: "#FF0000",
            fontFamily: '"Press Start 2P", cursive' // 커스텀 폰트 적용
        }).setOrigin(0.5).setInteractive();

        // Tween 애니메이션 설정
        this.tweens.add({
            targets: startButton,
            scale: { from: 1, to: 1.5 },
            duration: 750,
            yoyo: true,
            repeat: -1
        });

        startButton.on("pointerover", () => {
            startButton.setStyle({ backgroundColor: "#0000FF" });
            this.game.canvas.style.cursor = "pointer";
        });

        startButton.on("pointerout", () => {
            startButton.setStyle({ backgroundColor: null });
            this.game.canvas.style.cursor = "default";
        });

        startButton.on("pointerdown", () => {
            this.scene.start("controller");
        });
    }

    update() {
        // 필요시 업데이트 로직 추가
    }
}
