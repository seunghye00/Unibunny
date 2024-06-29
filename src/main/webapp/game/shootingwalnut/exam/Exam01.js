class Exam01 extends Phaser.Scene {

    constructor() { // Scene 에서 변수나 함수를 초기화 하는 곳
        super("Exam01");
    }

    preload() { // Scene 에 사용될 이미지, 음악 영상등의 자원을 로딩하는곳 (RAM에 적재 작업)

    }

    create() { // RAM에 적재된 자원을 바탕으로 인스턴스를 생성하는 곳

        this.cameras.main.setBackgroundColor('#ffffff'); // 배경색을 흰색으로 설정

        this.player = this.physics.add.sprite(100, 100, 50, 50); // 'player'는 로드된 이미지의 키 값 // physics.add = 물리체계에 추가하겠다 라는 뜻
        this.player.setCollideWorldBounds(true); // 화면을 벗어나지 못하게 했다!
        
        this.box = this.physics.add.sprite(250,250,50);
        this.box.setCollideWorldBounds(true); // 화면을 벗어나지 못하게 했다!
        this.box.setBounce(1); // 탄성
        this.box.setDrag(); // 공기저항
        this.box.setMass(5); // sprite의 잘량값

        // this.box.setImmovable(true); // sprite 움직임 을 고정

        this.physics.add.collider(this.player, this.box, function(player, box){
            console.log("충돌")
        }); // 플레이어와 박스 간의 충돌하게끔 하는 코드

        this.cursor = this.input.keyboard.createCursorKeys(); // 키보드 조작을 처리하기 위한 인스턴스
    }

    update() { // 무한루프를 반복하며 게임 내용을 채우는 곳 (기본 60 FPS)

        if (this.cursor.left.isDown) { // 왼쪽으로 이동
            //this.player.x -= 2;
            this.player.setVelocityX(-200);
        } else if (this.cursor.right.isDown) { // 오른쪽으로 이동
            //this.player.x += 2;
            this.player.setVelocityX(200);
        } else {
            this.player.setVelocityX(0);
        }


        if (this.cursor.up.isDown) { // 위로 이동
            //this.player.y -= 2;
            this.player.setVelocityY(-200);
        } else if (this.cursor.down.isDown) { // 아래로 이동
            //this.player.y += 2;
            this.player.setVelocityY(200);
        } else {
            this.player.setVelocityY(0);
        }
    }
}
