export default class Pacman {
    constructor(x, y, tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;

        this.currentMovingDirection = this.MovingDirection.right;

        this.pacmanAnimationTimerDefault = 2;
        this.pacmanAnimationTimer = this.pacmanAnimationTimerDefault;
        this.pacmanImageIndex = 1;

        // this.wakaSound = new Audio("sounds/waka.wav");
        //
        // this.powerDotSound = new Audio("sounds/power_dot.wav");
        // this.powerDotActive = false;
        // this.powerDotAboutToExpire = false;
        // this.timers = [];

        // this.eatGhostSound = new Audio("sounds/eat_ghost.wav");

        this.#loadPacmanImages();
    }

    MovingDirection = {
        right: 0,
        down: 1,
        left: 2,
        up: 3,
    };

    draw(ctx, pause) {
        if (!pause) {
            this.#animate();
        }

        const size = this.tileSize / 2;

        ctx.save();
        ctx.translate(this.x + size, this.y + size);
        ctx.rotate((this.currentMovingDirection * 90 * Math.PI) / 180);
        ctx.drawImage(
            this.pacmanImages[this.pacmanImageIndex],
            -size,
            -size,
            this.tileSize,
            this.tileSize
        );

        ctx.restore();
    }

    #loadPacmanImages() {
        const pacmanImage1 = new Image();
        pacmanImage1.src = "images/pacman/pac0.png";

        const pacmanImage2 = new Image();
        pacmanImage2.src = "images/pacman/pac1.png";

        const pacmanImage3 = new Image();
        pacmanImage3.src = "images/pacman/pac2.png";

        this.pacmanImages = [
            pacmanImage1,
            pacmanImage2,
            pacmanImage3,
            pacmanImage2,
        ];
    }

    keydown(keyCode) {
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
        if (this.pacmanAnimationTimer == null) {
            return;
        }
        this.pacmanAnimationTimer--;
        if (this.pacmanAnimationTimer == 0) {
            this.pacmanAnimationTimer = this.pacmanAnimationTimerDefault;
            this.pacmanImageIndex++;
            if (this.pacmanImageIndex == this.pacmanImages.length)
                this.pacmanImageIndex = 0;
        }
    }

    updatePosition(x, y, tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }

    #eatDot() {
        this.wakaSound.play();
    }

    #eatPowerDot() {
        this.powerDotSound.play();
    }

    #eatGhost() {
        this.eatGhostSound.play();
    }
}