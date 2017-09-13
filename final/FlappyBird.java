import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.event.*;
public class FlappyBird extends JPanel implements Runnable,KeyListener{
	JFrame frame=new JFrame("Flappy Bird - Samer Shaban");
	Thread t;
	int y=200;
	int x=250;
	Image image;
	int points;
	String birdPic="bird.gif";
	int xo=550;
	int xo2=550;
	int height =250;
	int gap = 180;
	int hs=0;
	int timer=4;
	String gameover ="Tap Spacebar or up arrow";
	boolean game = true;
	boolean controls = true;
	boolean start = false;
	boolean powerup=true;
	Polygon p;
	JOptionPane j = new JOptionPane();
	boolean up, down,left,right,hit=false;
	public FlappyBird()
	{

		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(617,638);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p=polygonSetup(x,y);
		t=new Thread(this);
		t.start();
		birdPic="bird.gif";
	}

	public Polygon polygonSetup(int x,int y)
	{
		int a[]={x,x+30,x+15,x,x+30};
		int b[]={y,y+30,y-15,y+30,y};

		p=new Polygon(a,b,a.length);
		return p;
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(new Color(0,175,255));
		g.fillRect(0,0,609,601);
		ImageIcon  i = new ImageIcon("pipe.png");
		image = i.getImage();
		g.drawImage(image,xo-3,height+gap,null);

		i = new ImageIcon("pipe2.png");
		image = i.getImage();
		g.drawImage(image,xo-3,-500+height,null);

		i = new ImageIcon(birdPic);
		image = i.getImage();
		g.drawImage(image,x-10,y,null);

		g.setColor(new Color(255,255,115));
		g.fillRect(0,550,601,50);

		g.setColor(new Color(75,255,75));
		g.fillRect(0,520,601,30);

		i = new ImageIcon("ground.png");
		image = i.getImage();
		g.drawImage(image,(xo)-600,520,null);

		//*/
		g.setColor(Color.WHITE);
		g.setFont(new Font("Agency FB", Font.BOLD, 70));
		g.drawString(Integer.toString(points), 50, 100);
		g.setFont(new Font("Agency FB", Font.BOLD, 70));
		g.drawString(gameover, 50, 300);
		g.setFont(new Font("Agency FB", Font.BOLD, 30));
		g.drawString("HiScore "+Integer.toString (hs), 50, 150);
	}

	public void run()
	{
		while(true){
		while(game)
		{

		if(!controls) {
		xo2=-50;
		gameover= "Game Over";
		}
		if(hit)
		{
		game = false;
		}
	if(controls){
		if(xo!=-50){
		if(start){
		xo--;
		xo2--;
			}
		}else{
		xo=600;
		xo2=600;
		height =((int)(Math.random()*30)+7)*10;
		}

	}

			if(up)
			{
				if(controls){
				if(y>=1){
				y--;
				y--;
				}
				}

			}else{
				if(y<=500){
					if(controls){
					if(start)
					y++;
					}
					else{
					y++;x++;
					y++;
					y++;
					}
				}
			}

			Rectangle gnd=new Rectangle(0,520,600,30);
			Rectangle pnt=new Rectangle(xo2,height,1,150);

			Rectangle o1=new Rectangle(xo,0,50,height);
			Rectangle o2=new Rectangle(xo,height+gap,50,(550-gap)-height);

			Rectangle bird=new Rectangle(x+30,y,1,50);
			if(bird.intersects(gnd)){

				controls=false;
				birdPic="bird.png";
				if(controls)
				gameover= "Game Over";
			}

			if(bird.intersects(o1)||bird.intersects(o2)){

				controls=false;
				birdPic="bird.png";
			}

			else hit=false;

			if(bird.intersects(pnt)){
				points++;
				if(points>hs)
				hs=points;
				if(points%5==0)
				gap-=10;
				System.out.println(points);
				if(points%10==0)
				timer--;
			}

			repaint();
			try
			{
				t.sleep(timer);
			}catch(InterruptedException e)
			{
			}

		}
	}
}
	public void keyTyped(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
	{
		if(controls==true&&game==true){
		if(start){
			if(ke.getKeyCode()==32||ke.getKeyCode()==38)
			up=true;

		}else{
		start = true;
		gameover="";
		}
		}
		else{
			if(y>=470){
				if(ke.getKeyCode()==32){
				restart();
			}
		}
		}
	}
	public void keyReleased(KeyEvent ke)
	{

		if(ke.getKeyCode()==32||ke.getKeyCode()==38)
			up=false;

	}
	public void restart(){
		//System.out.println("new game");
	game=true;
	controls=true;
	y=200;
	x=250;
	xo=600;
	xo2=550;
	gameover="";
	points=0;
	gap = 180;
	timer=4;

	int b =(int)(Math.random()*3);
			if(b==0)
			birdPic="bird.gif";
			else if(b==1)
			birdPic="bird.gif";
			else if(b==2)
		birdPic="bird.gif";

	}

	public static void main(String args[])
	{
		FlappyBird app=new FlappyBird();
	}

}