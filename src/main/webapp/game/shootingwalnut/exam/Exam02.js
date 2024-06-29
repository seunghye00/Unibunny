class Exam02 extends Phaser.Scene {

    constructor() { // Scene 에서 변수나 함수를 초기화 하는 곳
        super({ key: "Exam02" });
        this.boxes = [];
        this.frame = 0;
        this.timer = 0;


    }
    init() { // scene이 start 될 때마다 실행되는 함수
        // scene이 시작될 때 (다시 시작될 때) 초기화 시켜주는 작업
        this.timer = 0;
    }

    preload() { // Scene 에 사용될 이미지, 음악 영상등의 자원을 로딩하는곳 (RAM에 적재 작업)
        this.load.image('box', 'player1.png');
        this.load.image('player', 'player.png');
        this.load.image('background', 'background.jpg');
    }

    create() { // RAM에 적재된 자원을 바탕으로 인스턴스를 생성하는 곳
        this.cameras.main.setBackgroundColor('#ffffff'); // 배경색을 흰색으로 설정
        this.background = this.add.tileSprite(0, 0, this.cameras.main.width, this.cameras.main.height, 'background');
        this.background.setOrigin(0, 0);

        let bottomBoundary = this.add.rectangle(0, this.cameras.main.height + 30, this.cameras.main.width, 5, 0x0000);

        bottomBoundary.setOrigin(0, 0);
        this.physics.add.existing(bottomBoundary, true);
        this.physics.add.collider(this.boxes, bottomBoundary, (box, boundary) => {
            box.destroy();
            this.boxes.splice(this.boxes.indexOf(box), 1);
        });


        this.player = this.physics.add.sprite(250, 250, 'player');
        // this.player.setScale(); player 사진 크기 조절

        this.player.setScale(0.2); // 플레이어 이미지의 크기 조정 (0.1 배)
        this.player.setSize(0.3);
        this.player.setCollideWorldBounds(true);



        this.cursor = this.input.keyboard.createCursorKeys();

        this.physics.add.collider(this.boxes, this.player, (box, player) => {

            this.scene.start("GameOver");


        });

        this.timerText = this.add.text(this.cameras.main.width - 50, 10, ' 0 ', {
            fontSize: '32px',
            fill: '#FF0000'
        });
        let bottomBoundary1 = this.add.rectangle(0, this.cameras.main.height - 550, this.cameras.main.width, 5, 0x0000);
        bottomBoundary1.setOrigin(0, 0);
        this.physics.add.existing(bottomBoundary1, true);
        this.physics.add.collider(this.boxes, bottomBoundary1, (box, boundary1) => {
            box.destroy();
            this.boxes.splice(this.boxes.indexOf(box), 1);
        });
        let bottomBoundary2 = this.add.rectangle(0, this.cameras.main.height, this.cameras.main.width - 500, 5, 0x0000);
        bottomBoundary2.setOrigin(0, 0);
        this.physics.add.existing(bottomBoundary2, true);
        this.physics.add.collider(this.boxes, bottomBoundary2, (box, boundary2) => {
            box.destroy();
            this.boxes.splice(this.boxes.indexOf(box), 1);
        });
        let bottomBoundary3 = this.add.rectangle(0, this.cameras.main.height, this.cameras.main.width + 500, 5, 0x0000);
        bottomBoundary3.setOrigin(0, 0);
        this.physics.add.existing(bottomBoundary3, true);
        this.physics.add.collider(this.boxes, bottomBoundary3, (box, boundary3) => {
            box.destroy();
            this.boxes.splice(this.boxes.indexOf(box), 1);
        });

    }

    update() {
        this.background.tilePositionY -= 2;
        if (this.cursor.left.isDown) {
            this.player.setVelocityX(-200);
        } else if (this.cursor.right.isDown) {
            this.player.setVelocityX(200);
        } else {
            this.player.setVelocityX(0);
        }
        if (this.cursor.up.isDown) {
            this.player.setVelocityY(-200);
        } else if (this.cursor.down.isDown) {
            this.player.setVelocityY(200);
        } else {
            this.player.setVelocityY(0);
        }
        this.frame++;
        if (this.frame % 60 == 0) {
            this.timer++;
            this.timerText.setText(this.timer);
            let box = this.physics.add.sprite(Math.random() * (480 - 20 + 1) + 20, 20, 'box');
            box.setScale(0.3);
            box.setVelocityY(Math.random() * (200 - 100 + 1) + 100);
            this.boxes.push(box);
            if (this.timer >= 10) { // 10초 이상 20초 미만인 경우
                if (this.frame % 60 == 0) {
                    let box = this.physics.add.sprite(Math.random() * (480 - 20 + 1) + 20, this.cameras.main.height, 'box');
                    box.setScale(0.3);
                    box.setVelocityY(-(Math.random() * (200 - 100 + 1) + 100)); // 아래에서 위로 올라가도록 설정
                    this.boxes.push(box);
                }
            }
        }


        if (this.timer >= 20) { // 20초 이상인 경우
            if (this.frame % 60 == 0) {
                let box2 = this.physics.add.sprite(20, Math.random() * (this.cameras.main.height - 20 + 1) + 20, 'box');
                box2.setScale(0.3);
                box2.setVelocityX(Math.random() * (200 - 100 + 1) + 100);
                this.boxes.push(box2);
            }
            if (this.frame % 60 == 0) {
                let box3 = this.physics.add.sprite(this.cameras.main.width - 20, Math.random() * (this.cameras.main.height - 20 + 1) + 20, 'box');
                box3.setScale(0.3);
                box3.setVelocityX(-(Math.random() * (200 - 100 + 1) + 100));
                this.boxes.push(box3);
            }
        }
    }
}    