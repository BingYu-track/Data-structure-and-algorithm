package stack;

/**
 * @version 1.0
 * @Description: 浏览器前进后退功能实现
 * @author: bingyu
 * @date: 2019/4/20 14:45
 */
public class SimpleBrowser {

    private LinkeStack<String> firstStack; //栈一

    private LinkeStack<String> secondStack; //栈二

    public SimpleBrowser(){
        firstStack = new LinkeStack<String>();
        secondStack = new LinkeStack<String>();
    }

    //打开网站
    public void open(String url){
        if(!secondStack.isEmpty()){
            secondStack.clear();
        }
        firstStack.push(url);
        System.out.println(url +  " has been opened");
    }

    //后退
    public String goBack(){
        if(firstStack.isEmpty()){
            System.err.println("It's the last page. You can't back up.");
            return "";
        }
        secondStack.push(firstStack.pop()); //将栈一的栈顶元素出栈并放入栈二中
        return firstStack.getTopData();
    }

    //前进
    public String goForward(){
        if(secondStack.isEmpty()){
            System.err.println("This is the first page. You can't move forward.");
            return "";
        }
        firstStack.push(secondStack.pop()); //将栈二的栈顶元素出栈并放入栈一中
        return firstStack.getTopData();
    }


    public static void main(String[] args){
        SimpleBrowser browser = new SimpleBrowser();
        browser.open("https://bingyu.co");
        browser.open("www.baidu.com");
        browser.open("https://bingyu.co/archives");
        browser.open("www.jd.com");
        browser.open("https://github.com/BingYu-track");
        System.out.println(browser.goBack());
        System.out.println(browser.goBack());
        System.out.println(browser.goBack());
        browser.open("https://leetcode-cn.com/");
        System.out.println(browser.goBack());
        System.out.println(browser.goForward());
        System.out.println(browser.goForward());
        System.out.println(browser.goForward());
        System.out.println(browser.goForward());
        System.out.println(browser.goForward());
    }
}
