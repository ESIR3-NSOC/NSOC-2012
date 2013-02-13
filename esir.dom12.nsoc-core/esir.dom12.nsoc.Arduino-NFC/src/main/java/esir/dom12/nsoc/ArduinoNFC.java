package esir.dom12.nsoc;

import org.kevoree.annotation.*;
import org.kevoree.tools.arduino.framework.AbstractPeriodicArduinoComponent;
import org.kevoree.tools.arduino.framework.ArduinoGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 2/9/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Library(name = "ArduinoNFC")
@ComponentType
@Requires({
        @RequiredPort(name = "serial", type = PortType.MESSAGE, needCheckDependency = false)
})
public class ArduinoNFC extends AbstractPeriodicArduinoComponent {


    @Override
    public void generateClassHeader(ArduinoGenerator gen) {
        gen.appendNativeStatement("char id;\n");
    }

    @Override
    public void generateInit(ArduinoGenerator gen)
    {
        gen.appendNativeStatement("Serial.begin(9600);\n");
        gen.appendNativeStatement("Serial1.begin(9600);\n");

    }

    @Override
    public void generatePeriodic(ArduinoGenerator arduinoGenerator)
    {

        getGenerator().appendNativeStatement("if(Serial1.available()){");
        getGenerator().appendNativeStatement("id = Serial1.read();");
        getGenerator().appendNativeStatement("kmessage * smsg = (kmessage*) malloc(sizeof(kmessage));");
        getGenerator().appendNativeStatement("if (smsg){memset(smsg, 0, sizeof(kmessage));}");
        getGenerator().appendNativeStatement("sprintf(buf,\"%d\",id);\n");
        getGenerator().appendNativeStatement("smsg->value = buf;\n");
        getGenerator().appendNativeStatement("smsg->metric=\"c\";");
        getGenerator().appendNativeStatement("temp_rport(smsg);");
        getGenerator().appendNativeStatement("free(smsg);");
        getGenerator().appendNativeStatement("}");

    }
}