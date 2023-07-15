import cn.hutool.core.io.FileUtil;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
public class RegisterJFrame extends JFrame implements MouseListener {
    //注册相关代码写在这里
 ArrayList<User> allusers;
    JButton register = new JButton();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField password2 = new JPasswordField();

    public  RegisterJFrame(ArrayList<User> allusers){
        this.allusers=allusers;
        this.setSize(488,430);
        //设直界面的标题
        this.setTitle("拼图 V1.1 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面剧中
        this.setLocationRelativeTo(null);
        //设置默认的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameText.setBounds(116, 115, 47, 17);
        this.getContentPane().add(usernameText);
        //2.添加用户名输入框
        username.setBounds(195, 114, 200, 30);
        this.getContentPane().add(username);
        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordText.setBounds(130, 175, 32, 16);
        this.getContentPane().add(passwordText);
        //4.密码输入框
        password.setBounds(195, 170, 200, 30);
        this.getContentPane().add(password);
        //添加再次输入密码文字
        JLabel passwordText2 = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        passwordText2.setBounds(70, 240, 97, 17);
        this.getContentPane().add(passwordText2);
        //添加再次输入密码输入框
        password2.setBounds(195, 235, 200, 30);
        this.getContentPane().add(password2);

        //6.添加注册按钮
        register.setBounds(180, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);        this.getContentPane().add(register);
        //创建一个图片ImageIcon的对象
        ImageIcon background = new ImageIcon("image/register/background.png");
        //创建一个JLabel2的对象（管理容器）
        JLabel jLabel = new JLabel(background);
        //指定图片位置
        jLabel.setBounds(0,0,470,390);
        //把管理容器添加到界面中
        this.getContentPane().add(jLabel);
        //刷新一下界面
        this.getContentPane().repaint();
    }

    @Override
    public  void mouseClicked(MouseEvent e) {

        if (e.getSource() == register){
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String passwordInput2 = password2.getText();
            User u = new User();
            if (chazhao(allusers, usernameInput) == -1) {
                if (usernameInput.equals("")) showJDialog("用户名不能为空！");
                else {
                    u.setUsername(usernameInput);
                    if (checkp(passwordInput)) {
                        if (passwordInput.equals(passwordInput2)) {
                            u.setPassword(passwordInput);
                           allusers.add(u);
                            FileUtil.writeLines(allusers,"userInfo.txt","UTF-8");
                            showJDialog("注册成功！");
                            //关闭当前注册界面
                            this.setVisible(false);
                            //打开登录的主界面
                            new LoginJFrame();
                        } else showJDialog("两次密码不一致！");
                    }
                }
                }
            else{
                    showJDialog("此用户已存在 请登录");
                    //关闭当前注册界面
                    this.setVisible(false);
                    //打开登录的主界面
                    new LoginJFrame();
                }
            }
}
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);
        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        //让弹框展示出来
        jDialog.setVisible(true);
    }
    //判断用户在集合中是否存在


    private static int chazhao(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(name))
                return i;
        }
        return -1;
    }
    private static boolean checkp(String pass){
            int count1=0;int count2=0;
            if (pass.length()>=5){
                for (int i = 0; i <pass.length() ; i++) {
                    char n=pass.charAt(i);
                    if(n>='A'&&n<='Z'||n>='a'&&n<='z') count1++;
                    if (n>='0'&&n<='9')count2++;
                }
                if (count1+count2<pass.length()||count1==0||count2==0) {
                    showJDialog("密码必须由字母和数字组成！");
                    return false;
                }else
                    return true;
            }
            else {
                showJDialog("密码太短！");
                return false;
            }
    }


}
