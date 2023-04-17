import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener , ActionListener {
    //gameJFrame 就表示游戏主界面
    //跟游戏相关的逻辑都写在这里
    //创建功能下面的条目录
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem girl = new JMenuItem("女");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem animal = new JMenuItem("动物");
    JMenu change = new JMenu("更换图片");

    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭");
    JMenuItem accountItem = new JMenuItem("微信");
    int [][] date =new int[4][4];
    Random r=new Random();
    //定义一个变量统计部署
    int count=0;
    //定义m n 来记录当前路径
    int m=1;//r.nextInt(3);
    int n=6;//r.nextInt(8);
    //用X Y 记录空白方块在数组中的位置
    int x=0;
    int y=0;
    //定义路径
    String[][] path ={
            {"image\\animal\\animal1\\","image\\animal\\animal2\\","image\\animal\\animal3\\","image/animal/animal4/","image/animal/animal5/","image/animal/animal6/","image/animal/animal7/","image/animal/animal8/"},//animal  8
            {"image/girl/girl1/","image/girl/girl2/","image/girl/girl3/","image/girl/girl4/","image\\girl\\girl5\\","image/girl/girl6/","image/girl/girl7/","image/girl/girl8/","image/girl/girl9/","image/girl/girl10/","image/girl/girl11/"},//girl   11
            {"image/sport/sport1/","image/sport/sport2/","image/sport/sport3/","image/sport/sport4/","image/sport/sport5/","image/sport/sport6/","image/sport/sport7/","image/sport/sport8/","image/sport/sport9/","image/sport/sport10/"}//sport   10

    };
    //定义正确的数组  用来判断胜利
    int[][] win ={
            {1,5,9,13},
            {2,6,10,14},
            {3,7,11,15},
            {4,8,12,0}
    };

    public GameJFrame(){
        //初始化界面
        setGameJFrame();
        //初始化菜单
        setMeu();
        //初始化数据
        initDate();
        //初始化图片
        initImage();
        //让界面显示出来，写在最后
        this.setVisible(true);
    }

    private void initDate() {
        //定义一个一维数组
        int[]tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //打乱数组中数据的顺序

        for (int i = 0; i <tempArr.length ; i++) {
            //获取一个随机索引
            int index=r.nextInt(tempArr.length);
            int temp=tempArr[index];
            tempArr[index]=tempArr[i];
            tempArr[i]=temp;

        }
        //for (int i = 0; i < 16; i++) {
        //    System.out.println(tempArr[i]);
        //}
        //遍历一维数组将其中的数据放入二维数组中
        for (int i = 0; i < tempArr.length; i++) {
          if (tempArr[i]==0) {
              x = i / 4;
              y = i % 4;
          }
              date[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //清空已经出现的图片
        this.getContentPane().removeAll();
        //判断是否胜利
        if (victory()){
            JLabel winJLabel=new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(200,310,197,73);
            this.getContentPane().add(winJLabel);
        }
        //创建计数图容器
        JLabel stepCount=new JLabel("步数"+count);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取要加载图片的序号
                int num=date[i][j] ;
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path[m][n] +num+".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(i * 105+83, 105*j+134, 105, 105);
                //给图片添加边框
                //0表示图片凸起来
                //1表示图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

            }
        }
        //创建一个图片ImageIcon的对象
        ImageIcon background = new ImageIcon("image\\background.png");
        //创建一个JLabel2的对象（管理容器）
        JLabel jLabel = new JLabel(background);
        //指定图片位置
        jLabel.setBounds(40,40,508,560);
        //把管理容器添加到界面中
        this.getContentPane().add(jLabel);
        //刷新一下界面
        this.getContentPane().repaint();
    }


    private void setGameJFrame() {
        //设置界面的宽高
        this.setSize(605, 680);
        //设直界面的标题
        this.setTitle("拼图单机版 v1.1");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面剧中
        this.setLocationRelativeTo(null);
        //设置默认的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置
        this.setLayout(null);
        //给整个界面添加键盘监视
        this.addKeyListener(this);
        //添加动作监听
    }

    private void setMeu() {
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上选项的对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我");

        //将每一个选项下面的条目录添加到选项当中
        functionJMenu.add(reLoginItem);
        functionJMenu.add(change);
        functionJMenu.add(replayItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);
        //给更换图片添加条目
        change.add(girl);
        change.add(sport);
        change.add(animal);
        //给条目绑定事件

        replayItem.addActionListener(this);
        girl.addActionListener(this);
        sport.addActionListener(this);
        animal.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        //将菜单选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override//按下不松时调用此方法
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
        if (code==65){
            //把界面中的图片全部删除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            //创建一个图片ImageIcon的对象
            ImageIcon all = new ImageIcon(path[m][n]+"all.jpg");
            //创建一个JLabel的对象（管理容器）
            JLabel jLabel0 = new JLabel(all);
            jLabel0.setBounds(83,134,420,420);
            this.getContentPane().add(jLabel0);
            //创建一个图片ImageIcon的对象
            ImageIcon background = new ImageIcon("image\\background.png");
            //创建一个JLabel2的对象（管理容器）
            JLabel jLabel = new JLabel(background);
            //指定图片位置
            jLabel.setBounds(40,40,508,560);
            //把管理容器添加到界面中
            this.getContentPane().add(jLabel);
            //刷新一下界面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
//对上下左右进行判断
        int code=e.getKeyCode();
        if (victory()) {return;}
            if (code == 37) {
                if (x > 0) {
                    date[x][y] = date[x - 1][y];
                    date[x - 1][y] = 0;
                    x--;
                    count++;
                    initImage();
                }
            } else if (code == 38) {
                if (y > 0) {
                    date[x][y] = date[x][y - 1];
                    date[x][y - 1] = 0;
                    y--;
                    count++;
                    initImage();
                }
            } else if (code == 39) {
                if (x < 3) {
                    date[x][y] = date[x + 1][y];
                    date[x + 1][y] = 0;
                    x++;
                    count++;
                    initImage();
                }
            } else if (code == 40) {
                if (y < 3) {
                    date[x][y] = date[x][y + 1];
                    date[x][y + 1] = 0;
                    y++;
                    count++;
                    initImage();
                }
            }

       // 查看原图
        else if (code==65) {
            initImage();
        } else if (code==87) {
            date=new int[][]{
                    {1,5,9,13},
                    {2,6,10,14},
                    {3,7,11,15},
                    {4,8,12,0}
            };
            x=y=3;
            initImage();
        }

    }
    //判断是否胜利  返回布尔类型
    public boolean victory(){
        for (int i = 0; i <date.length ; i++) {
            for (int j = 0; j < date[i].length; j++) {
                if (date[i][j]!=win[i][j]){
                    return false;
                }
            }
        }

     return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取点击的条目对象
        Object object= e.getSource();
        //判断  并触发事件
        if (object==replayItem){
            //再次打乱date数组
            initDate();
            //计步器归零
            count =0;
            //重新加载图片
            initImage();
        } else if (object==reLoginItem) {
            //关闭当前界面
            this.setVisible(false);
            //返回登录界面
            new LoginJFrame();

        } else if (object==closeItem) {
            System.exit(0);
        } else if (object==accountItem) {
          //创建一个弹窗对象
            JDialog jDialog=new JDialog();
            //创建弹窗图片的管理容器
            JLabel jLabel=new JLabel(new ImageIcon("image\\about .png"));
            //设置宽高并添加
            jLabel.setBounds(0,0,759,729);
            jDialog.getContentPane().add(jLabel);
            //给弹窗设置大小
            jDialog.setSize(760,760);
            jDialog.setAlwaysOnTop(true);
            //让弹窗剧中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭  无法进行其他操作
            jDialog.setModal(true);
            //让弹窗显示
            jDialog.setVisible(true);
        } else if (object==girl) {
            m=1;n=r.nextInt(11);
            //再次打乱date数组
            initDate();
            //计步器归零
            count =0;
            //重新加载图片
            initImage();
        }  else if (object==sport) {
            m=2;n=r.nextInt(10);
            //再次打乱date数组
            initDate();
            //计步器归零
            count =0;
            //重新加载图片
            initImage();
        } else if (object==animal) {
            m=0;n=r.nextInt(8);
            //再次打乱date数组
            initDate();
            //计步器归零
            count =0;
            //重新加载图片
            initImage();

        }

    }
}
