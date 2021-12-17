export default class Ghost {
    constructor(x, y, tileSize, type) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.type = type;

        this.currentMovingDirection = this.MovingDirection.up;
        this.ghostAnimationTimer = 3;
        this.ghostAnimationTimerDefault = 3;
        this.ghostImageIndex = true
        this.blink = 0

        this.#loadGhostImages();
    }

    MovingDirection = {
        right: 0,
        down: 1,
        left: 2,
        up: 3,
    };

    draw(ctx, pause) {
        if(!pause) {
            this.#animate();
        }

        const size = this.tileSize / 2;

        ctx.save();
        ctx.translate(this.x + size, this.y + size);
        if (this.ghostImageIndex) {
            if (this.type == "frighten") {
                ctx.drawImage(
                    this.ghostFrigthenImages[this.blink],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            } else if(this.type == "eyes") {
                ctx.drawImage(
                    this.ghostEyesImages[this.currentMovingDirection],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            } else {
                ctx.drawImage(
                    this.ghostImages[this.currentMovingDirection],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            }
        }  else {
            if (this.type == "frighten") {
                ctx.drawImage(
                    this.ghostFrigthenImages2[this.blink],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            } else if(this.type == "eyes") {
                ctx.drawImage(
                    this.ghostEyesImages[this.currentMovingDirection],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            } else {
                ctx.drawImage(
                    this.ghostImages2[this.currentMovingDirection],
                    -size,
                    -size,
                    this.tileSize,
                    this.tileSize
                );
            }
        }

        ctx.restore();
    }

    #loadGhostImages() {

        const ghostImageRight = new Image();
        ghostImageRight.src = "images/ghosts/" + this.type + "/right.png";

        const ghostImageDown = new Image();
        ghostImageDown.src = "images/ghosts/" + this.type + "/down.png";

        const ghostImageLeft = new Image();
        ghostImageLeft.src = "images/ghosts/" + this.type + "/left.png";

        const ghostImageUp = new Image();
        ghostImageUp.src = "images/ghosts/" + this.type + "/up.png";

        this.ghostImages = [
            ghostImageRight,
            ghostImageDown,
            ghostImageLeft,
            ghostImageUp,
        ];

        const ghostImageRight2 = new Image();
        ghostImageRight2.src = "images/ghosts/" + this.type + "/right2.png";

        const ghostImageDown2 = new Image();
        ghostImageDown2.src = "images/ghosts/" + this.type + "/down2.png";

        const ghostImageLeft2 = new Image();
        ghostImageLeft2.src = "images/ghosts/" + this.type + "/left2.png";

        const ghostImageUp2 = new Image();
        ghostImageUp2.src = "images/ghosts/" + this.type + "/up2.png";

        this.ghostImages2 = [
            ghostImageRight2,
            ghostImageDown2,
            ghostImageLeft2,
            ghostImageUp2,
        ];

        const ghostImageFrighten1 = new Image();
        ghostImageFrighten1.src = "images/ghosts/frighten/frighten1.png";

        const ghostImageFrighten2 = new Image();
        ghostImageFrighten2.src = "images/ghosts/frighten/frighten2.png";

        const flash = new Image();
        flash.src = "images/ghosts/frighten/flash.png";

        this.ghostFrigthenImages = [
            ghostImageFrighten1,
            ghostImageFrighten1
        ];
        this.ghostFrigthenImages2 = [
            ghostImageFrighten2,
            flash
        ]

        const ghostImageEyesRight = new Image();
        ghostImageEyesRight.src = "images/ghosts/eyes/right.png";

        const ghostImageEyesDown = new Image();
        ghostImageEyesDown.src = "images/ghosts/eyes/down.png";

        const ghostImageEyesLeft = new Image();
        ghostImageEyesLeft.src = "images/ghosts/eyes/left.png";

        const ghostImageEyesUp = new Image();
        ghostImageEyesUp.src = "images/ghosts/eyes/up.png";

        this.ghostEyesImages = [
            ghostImageEyesRight,
            ghostImageEyesDown,
            ghostImageEyesLeft,
            ghostImageEyesUp,
        ];
    }

    keydown = (keyCode) => {

        //up
        if (keyCode == 38) {
            this.currentMovingDirection = this.MovingDirection.up;
        }
        //down
        if (keyCode == 40) {
            this.currentMovingDirection = this.MovingDirection.down;
        }
        //left
        if (keyCode == 37) {
            this.currentMovingDirection = this.MovingDirection.left;
        }
        //right
        if (keyCode == 39) {
            this.currentMovingDirection = this.MovingDirection.right;
        }
    };

    #animate() {
        if (this.ghostAnimationTimer == null) {
            return;
        }
        this.ghostAnimationTimer--;
        if (this.ghostAnimationTimer == 0) {
            this.ghostAnimationTimer = this.ghostAnimationTimerDefault;
            this.ghostImageIndex = !this.ghostImageIndex;
        }
    }

    updatePosition(x, y, tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }

    changeGhost(type) {
        if (type != "frighten") {
            this.blink = 0;
        }
        this.type = type;
    }

    setBlink() {
        this.blink = 1;
    }
}