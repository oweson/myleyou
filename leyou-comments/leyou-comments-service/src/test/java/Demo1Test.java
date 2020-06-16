import java.util.Optional;

public class Demo1Test {
    public static void main(String[] args) {
        // 如果为空就调用执行orElse后面的代码；
        Object hahah = Optional.ofNullable("php").orElse("hahah");
        System.out.println(hahah);
    }
}
