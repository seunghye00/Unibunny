class Controller extends Phaser.Scene {
    
    
    constructor() {
        super({ key: "controller" });
        this.lastFired = 0;
        this.gameOver = false;
        
    }

    init(){ // scene 이 start 될때마다 실행되는 함수
        this.score = 0;
        this.gameOver = false;
        var gameId = 3;
        StartGameFunctions.sendGameid(gameId);
    }

    preload() {
        this.load.image('player', 'img/player.png'); // 플레이어 이미지 로드
        this.load.image('bullet', 'img/bullet.png'); // 총알 이미지 로드
        this.load.image('enemy', 'img/enemy.png'); // 적 이미지 로드
        this.load.image('background', 'img/background.jpg'); // 배경 이미지 로드
    }

    create() {
        this.cameras.main.setBackgroundColor('#ffffff'); // 배경색을 흰색으로 설정

        // 배경 이미지 추가
        this.background = this.add.image(0, 0, 'background').setOrigin(0, 0);
        this.background.displayWidth = this.cameras.main.width;
        this.background.displayHeight = this.cameras.main.height;

        // 플레이어 생성 및 설정
        this.player = this.physics.add.image(250, 450, 'player').setCollideWorldBounds(true);
        this.player.setScale(0.25); // 원하는 사이즈 / 원본 이미지 사이즈 = scaleFactor

        // 키보드 입력 생성
        this.cursors = this.input.keyboard.createCursorKeys();

        // 총알 그룹 생성
        this.bullets = this.physics.add.group({
            classType: Phaser.Physics.Arcade.Image,
            maxSize: 20, // 최대 총알 수
            runChildUpdate: true
        });

        // 적 그룹 생성
        this.enemies = this.physics.add.group({
            classType: Phaser.Physics.Arcade.Image,
            maxSize: 10, // 최대 적 수
            runChildUpdate: true
        });

        // 적 생성 타이머 설정
        this.enemyTimer = this.time.addEvent({
            delay: 1000, // 1초마다 적 생성
            callback: this.spawnEnemy,
            callbackScope: this,
            loop: true
        });

        // 충돌 설정: 총알과 적
        this.physics.add.collider(this.bullets, this.enemies, this.hitEnemy, null, this);
        // 충돌 설정: 플레이어와 적
        this.physics.add.collider(this.player, this.enemies, this.hitPlayer, null, this);

        // 점수 텍스트 추가
        this.scoreText = this.add.text(16, 16, 'Score: 0', { fontSize: '30px', fill: "#FFFFFF" });
    }

    update(time, delta) {
        if (this.gameOver) {
            return; // 게임 오버 상태에서는 업데이트를 중지합니다.
        }

        // 플레이어 속도 초기화
        this.player.setVelocity(0);

        // 키보드 입력에 따라 플레이어 이동
        if (this.cursors.left.isDown) {
            this.player.setVelocityX(-200);
        } else if (this.cursors.right.isDown) {
            this.player.setVelocityX(200);
        }

        if (this.cursors.up.isDown) {
            this.player.setVelocityY(-200);
        } else if (this.cursors.down.isDown) {
            this.player.setVelocityY(200);
        }

        // 스페이스바 입력에 따라 총알 발사
        if (this.cursors.space.isDown && time > this.lastFired) {
            let bullet = this.bullets.get(this.player.x, this.player.y, 'bullet'); // 총알 이미지 설정

            if (bullet) {
                bullet.setActive(true);
                bullet.setVisible(true);
                bullet.setVelocityY(-300);
                bullet.setScale(0.25); // 총알 크기 조정
                bullet.body.setSize(bullet.width * 0.5, bullet.height * 0.5); // 충돌 영역 조정
                this.lastFired = time + 500;
            }
        }

        // 화면을 벗어난 총알 비활성화
        this.bullets.children.each(function (bullet) {
            if (bullet.y < 0) {
                bullet.setActive(false);
                bullet.setVisible(false);
            }
        });

        // 적 이동 및 하단 충돌 체크
        this.enemies.children.each(function (enemy) {
            if (enemy.active) {
                enemy.setVelocityY(100);
                if (enemy.y > 500) { // 적이 화면 하단에 도달하면
                    this.hitPlayer(this.player, enemy);
                }
            }
        }, this);
    }

    spawnEnemy() {
        if (this.gameOver) {
            return; // 게임 오버 상태에서는 적을 생성하지 않습니다.
        }

        // 적이 완전히 화면 안에 생성되도록 x 좌표 조정
        let enemyX = Phaser.Math.Between(25, 475); // 적의 너비를 고려하여 x 좌표 범위 설정
        let enemy = this.enemies.get(enemyX, -50, 'enemy'); // y 좌표를 화면 위로 설정

        if (enemy) {
            enemy.setActive(true);
            enemy.setVisible(true);
            enemy.setVelocityY(100); // y 방향으로만 내려오도록 속도 설정
            enemy.setScale(0.75); // 적 크기 조정
            enemy.body.setSize(enemy.width * 0.5, enemy.height * 0.5); // 충돌 영역 조정

            // 좌우로 흔들리는 패턴 추가
            this.tweens.add({
                targets: enemy,
                x: enemy.x + Phaser.Math.Between(-100, 100),
                duration: 2000,
                yoyo: true,
                repeat: -1,
                ease: 'Sine.easeInOut'
            });
        }
    }

    hitEnemy(bullet, enemy) {
        if (!bullet.active || !enemy.active) {
            return;
        }

        bullet.setActive(false);
        bullet.setVisible(false);
        enemy.setActive(false);
        enemy.setVisible(false);
        enemy.setVelocity(0, 0); // 적의 속도를 0으로 설정
        enemy.setPosition(-50, -50); // 적을 화면 밖으로 이동
        this.score += 10; // 점수 증가
        this.scoreText.setText('Score: ' + this.score); // 점수 업데이트
    }

    hitPlayer(player, enemy) {
        if (!enemy.active) {
            return;
        }

        this.physics.pause(); // 물리 엔진 일시정지
        player.setTint(0xff0000); // 플레이어 색상 변경 (빨간색)
        this.gameOver = true; // 게임 오버 플래그 설정
        this.scene.start("gameover", { score: this.score, gameId: 3 }); // 게임 오버 씬으로 점수 및 gameId 전달
    }
}
