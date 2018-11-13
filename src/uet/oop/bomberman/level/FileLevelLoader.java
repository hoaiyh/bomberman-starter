package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;

	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}

	@Override
	public void loadLevel(int level)  {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
//		String path = "";
//		switch(level){
//			case 1:
//				path = "level1.txt";
//				break;
//			case 2:
//				path ="leve2.txt";
//				break;
//			case 3:
//				path="level3.txt";
//				break;
//		}
		try{
			InputStream is = new FileInputStream("res/levels/level" + level + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = br.readLine();
			StringTokenizer st =new StringTokenizer(s);
			_level = Integer.valueOf(st.nextToken());
			_height = Integer.valueOf(st.nextToken());
			_width = Integer.valueOf(st.nextToken());
			_map = new char[_height][_width];
			for(int i=0;i<_height;i++)
			{
				String data = br.readLine();
				char [] line = data.toCharArray();
				for(int j = 0;j<_width;j++){
					_map[i][j] = line[j];
				}
			}

		}catch(IOException e) {
			System.out.println("ERROR!");
		}

	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		// thêm Wall
		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
				int pos = x + y * _width;
				char b = _map[y][x];
				switch(b){
					//them wall
					case '#':
						_board.addEntity(pos,new Wall(x,y,Sprite.wall));
						break;
					//them Bomber
					case 'p':
//
						_board.addCharacter(new Bomber(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						Screen.setOffset(0,0);
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					//them Enemy Ballon
					case '1':
//
						_board.addCharacter(new Balloon(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					//them Enemy Oneal
					case '2':

						_board.addCharacter(new Oneal(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					//them Brick
					case '*':

						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new Brick(x,y,Sprite.brick)));
						break;
					// thêm Item kèm Brick che phủ ở trên
					case 'x':
						//Portal

						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new Portal(x,y,Sprite.portal),new Brick(x,y,Sprite.brick)));
						break;
					case 'b':
						//Bomb Item

						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new BombItem(x,y,Sprite.powerup_bombs),new Brick(x,y,Sprite.brick)));
						break;
					//them Flame Item
					case 'f':

						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new FlameItem(x,y,Sprite.powerup_flames),new Brick(x,y,Sprite.brick)));
						break;
					//them Speed Item
					case 's':

						_board.addEntity(pos,new LayeredEntity(x,y,new Grass (x,y,Sprite.grass),new SpeedItem(x,y,Sprite.powerup_speed),new Brick(x,y,Sprite.brick)));
						break;
					default:

						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;


				}


			}

		}

//		// thêm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//		// thêm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thêm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//
//		// thêm Item kèm Brick che phủ ở trên
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
	}

}
