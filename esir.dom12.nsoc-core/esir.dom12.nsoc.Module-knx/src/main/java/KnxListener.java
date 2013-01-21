/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 1/21/13
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public interface KnxListener {

    public String getState(String add);
    public void sendData(String data);
}
