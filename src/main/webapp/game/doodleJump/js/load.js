var game = new Phaser.Game(750, 1334, Phaser.CANVAS, "game");
var Load = function() {};
Load.prototype = {
    setScale: function(val){
        val = val * gameScale * 2;
        return val;
    },
    startLoad: function() {
        this.load.image('bg', 'images/bg.jpg');
        this.load.image('btn', 'images/btn.png');
        this.load.atlas('atlas', 'images/atlas.png', 'images/atlas.json');
        this.load.audio('bg', 'music/bg.mp3');
        this.load.audio('jump', 'music/jump.mp3');
        this.load.audio('spring', 'music/spring.mp3');
        this.load.audio('false1', 'music/false1.mp3');
        this.load.audio('false2', 'music/false2.mp3');
        this.load.audio('click', 'music/click.mp3');
        this.load.start();
    },
    loadStart: function() {
        this.text.setText("로딩 중 ...");
    },
    fileComplete: function(progress) {
        this.text.setText(progress + "%");
    },
    loadComplete: function() {
        this.text.setText("시작 중 ...");
        music[0] = this.add.audio('bg', 0.4, true);
        music[1] = this.add.audio('jump');
        music[2] = this.add.audio('spring');
        music[3] = this.add.audio('false1', 0.2);
        music[4] = this.add.audio('false2');
        music[5] = this.add.audio('click');
        this.sound.setDecodedCallback(music, function(){
            music[0].play();
            this.tips();
        }, this);
    },
    create: function() {
        this.stage.backgroundColor = '#edffd9';
        this.scale.scaleMode = Phaser.ScaleManager.EXACT_FIT;
        this.scale.setGameSize(gameWidth * 2, gameHeight * 2);
        this.scale.setMinMax(320, 480, 750, 1366);
        this.stage.disableVisibilityChange = true;
        this.text = this.add.text(this.world.centerX, this.world.centerY, '', {font: this.setScale(50) + "px myFont", fill: '#6c6f3a'});
        this.text.anchor.set(0.5);
        this.load.onLoadStart.add(this.loadStart, this);
        this.load.onFileComplete.add(this.fileComplete, this);
        this.load.onLoadComplete.add(this.loadComplete, this);
        this.startLoad();
    },
    tips: function(){
        this.bg = this.add.sprite(0, 0, 'bg');
        this.bg.width = gameWidth * 2;
        this.bg.height = gameHeight * 2;

        this.tipsbg = this.add.graphics(0, 0);
        this.tipsbg.beginFill(0x000000, 0.7);
        this.tipsbg.drawRect(0, 0, gameWidth * 2, gameHeight * 2);
        this.tipsbg.endFill();
        this.tipsBackground = this.add.sprite(0, 0, this.tipsbg.generateTexture());
        this.tipsbg.destroy();

        this.title = this.add.text(this.world.centerX, this.setScale(80), '작은 팁', {font: this.setScale(50) + "px myFont", fill: '#fff'});
        this.title.anchor.set(0.5);

        this.addTips(0, 0, this.setScale(1.3), 'platform0', '일반 바닥');
        this.addTips(0, this.setScale(100) * 1, this.setScale(1.3), 'platform3', '한 번만 닿을 수 있는 바닥');
        this.addTips(0, this.setScale(100) * 2, this.setScale(1.3), 'platform1', '움직이는 바닥');
        this.addTips(this.setScale(-10), this.setScale(100) * 3, this.setScale(1.3), 'platform2', '부서지는 바닥');
        this.addTips(this.setScale(105), this.setScale(100) * 4, this.setScale(1.3), 'bonus0', '스프링');
        this.addTips(this.setScale(40), this.setScale(100) * 5, this.setScale(0.8), 'obstacle4', '절대 이 블랙홀에 닿지 마세요!');

        this.tipsBtn = new this.addBtn(this.world.centerX - this.setScale(150), this.setScale(800), this.setScale(2.5), 'platform0', '게임 시작', '#d9cc43', '#333', this.setScale(36), null);
        this.tipsBtn.Btn.events.onInputDown.add(function(){
            music[5].play();
            game.state.start('Game');
            StartGameFunctions.sendGameid(gameId);
        }, this);
    },
    addTips: function(x, y, scale, frame, texts){
        var sprite = this.add.sprite(this.setScale(60), this.setScale(150) + y, 'atlas', frame);
        sprite.scale.set(scale);
        var text = this.add.text(sprite.x + sprite.width + this.setScale(50) + x, sprite.y, texts, {font: this.setScale(30)+"px", fill: '#d9cc43'});
	},
	addBtn: function(x,y,scale,img,texts,color,stroke,fontSize){
		var typa = typa || null;
		var typb = typb || null;
		this.Btn = game.add.sprite(x,y, 'atlas', img);
		this.Btn.scale.set(scale);
		this.BtnText = game.add.text(0, 0, texts, {font: fontSize+"px myFont", fill: color, boundsAlignH: "center", boundsAlignV: "middle"});
		this.BtnText.setTextBounds(x, y, this.Btn.width, this.Btn.height);
		this.BtnText.stroke = stroke;
		this.BtnText.strokeThickness = 8;
		this.Btn.inputEnabled = true;
		return this
	}
};