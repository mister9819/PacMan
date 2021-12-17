import Treasure from "./Treasure.js";

export default class TileMap {
    constructor(tileSize) {
        this.tileSize = tileSize;
        this.charaterSize = tileSize + 5;

        this.dot = new Image();
        this.dot.src = "images/dot.png";

        this.pill = new Image();
        this.pill.src = "images/pill.png";

        this.wall = new Image();
        this.wall.src = "images/walls/wall4.png";

        this.blank = new Image();
        this.blank.src = "images/blank.png";

        this.treasure = new Image();
        this.treasure.src = "images/fruits/Fruit1.png"

        this.powerDot = this.pill;
        this.powerDotAnmationTimerDefault = 15;
        this.powerDotAnmationTimer = this.powerDotAnmationTimerDefault;

        // 0 - Empty
        // 1 - Wall
        // 2 - Dot
        // 3 - green
        // 4 - yellow
        // 5 - red
        // 6 - blue
        // 7 - Pill
        // 8 - Pacman
        // 9 - Lair
        // 10 - Eyes
        // TODO: Fruit???
        // TODO: T treasure?
        // TODO: T Map
        // TODO: Spawn a pacman which randomly walk
        // this.map = [
        //     [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //     [1, 7, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 7, 1],
        //     [1, 2, 1, 1, 10, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 11, 1, 1, 2, 1],
        //     [1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1],
        //     [1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1],
        //     [1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1],
        //     [1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1],
        //     [1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1],
        //     [0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0],
        //     [0, 0, 0, 1, 2, 1, 2, 1, 9, 9, 9, 9, 1, 2, 1, 2, 1, 0, 0, 0],
        //     [1, 1, 1, 1, 2, 1, 2, 1, 9, 3, 9, 4, 1, 2, 1, 2, 1, 1, 1, 1],
        //     [0, 0, 0, 0, 2, 2, 2, 1, 9, 9, 5, 9, 1, 2, 2, 2, 0, 0, 0, 0],
        //     [1, 1, 1, 1, 2, 1, 2, 1, 9, 9, 9, 6, 1, 2, 1, 2, 1, 1, 1, 1],
        //     [0, 0, 0, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 0, 0, 0],
        //     [0, 0, 0, 1, 2, 1, 2, 0, 0, 0, 8, 0, 0, 2, 1, 2, 1, 0, 0, 0],
        //     [1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1],
        //     [1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1],
        //     [1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1],
        //     [1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1],
        //     [1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1],
        //     [1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1],
        //     [1, 7, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 7, 1],
        //     [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        // ];
    }

    setMap(map) {
        this.map = map;
    }

    setCanvasSize(canvas) {
        canvas.width = this.map[0].length * this.tileSize;
        canvas.height = this.map.length * this.tileSize;
    }

    drawWall(ctx) {
        for (let row = 0; row < this.map.length; row++) {
            for (let column = 0; column < this.map[row].length; column++) {
                let tile = this.map[row][column];
                if (tile === 1) {
                    this.#drawWall(ctx, column, row, this.tileSize);
                }
            }
        }
    }

    draw(ctx) {
        let hasDrawnDot = false;
        for (let row = 0; row < this.map.length; row++) {
            for (let column = 0; column < this.map[row].length; column++) {
                let tile = this.map[row][column];
                if (tile === 2) {
                    hasDrawnDot = true;
                    this.#drawDot(ctx, column, row, this.tileSize);
                } else if (tile === 7) {
                    hasDrawnDot = true;
                    this.#drawPowerDot(ctx, column, row, this.tileSize);
                } else if (tile === 9) {
                    this.#drawTreasure(ctx, column, row, this.tileSize);
                }
            }
        }
        return hasDrawnDot;
    }

    #drawDot(ctx, column, row, size) {
        ctx.drawImage(
            this.dot,
            column * this.tileSize,
            row * this.tileSize,
            size,
            size
        );
    }

    #drawPowerDot(ctx, column, row, size) {
        this.powerDotAnmationTimer--;
        if (this.powerDotAnmationTimer === 0) {
            this.powerDotAnmationTimer = this.powerDotAnmationTimerDefault;
            if (this.powerDot == this.pill) {
                this.powerDot = this.blank;
            } else {
                this.powerDot = this.pill;
            }
        }
        ctx.drawImage(this.powerDot, column * size, row * size, size, size);
    }

    #drawWall(ctx, column, row, size) {
        ctx.drawImage(
            this.wall,
            column * this.tileSize,
            row * this.tileSize,
            size,
            size
        );
    }

    #drawTreasure(ctx, column, row, size) {
        ctx.drawImage(
            this.treasure,
            column * this.tileSize,
            row * this.tileSize,
            size,
            size
        );
    }

    getPacman() {
        for (let row = 0; row < this.map.length; row++) {
            for (let column = 0; column < this.map[row].length; column++) {
                let tile = this.map[row][column];
                if (tile === 8) {
                    this.map[row][column] = 0;
                    return [
                        column * this.tileSize,
                        row * this.tileSize,
                    ]
                }
            }
        }
    }

    // 3 - pink
    // 4 - yellow
    // 5 - red
    // 6 - blue
    getGhost(type) {
        let ghost;
        switch (type) {
            case "red":
                ghost = 5
                break
            case "pink":
                ghost = 3
                break
            case "blue":
                ghost = 6
                break
            case "yellow":
                ghost = 4
                break
            case "eyes":
                ghost = 10
                break
            case "frighten":
                ghost = 11
                break
        }
        for (let row = 0; row < this.map.length; row++) {
            for (let column = 0; column < this.map[row].length; column++) {
                let tile = this.map[row][column];
                if (tile === ghost) {
                    this.map[row][column] = 0;
                    return [
                        column * this.tileSize,
                        row * this.tileSize,
                    ]
                }
            }
        }
    }

    #drawBlank(ctx, column, row, size) {
        ctx.fillStyle = "black";
        ctx.fillRect(column * this.tileSize, row * this.tileSize, size, size);
    }

    setWall(src) {
        this.wall.src = src;
    }

}