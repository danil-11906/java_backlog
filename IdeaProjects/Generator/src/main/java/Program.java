//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using WebAddressbookTests;
//using System.IO;
//using System.Xml;
//using System.Xml.Serialization;
//using System.Threading.Tasks;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import com.sun.xml.internal.txw2.output.XmlSerializer;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;

class Program
{
    static void Main(String[] args)
    {
        String type = args[0];
        int count = Convert.ToInt32(args[1]);
        String filename = args[2];
        String format = args[3];
        if (type == "groups")
        {
            GenerateForGroups(count, filename, format);
        }
        else
        {
            System.out.println("Unrecognized type of data" + type);
        }
    }

    static void GenerateForGroups(int count, String filename, String format)
    {
        List<GroupData> groups = new List<GroupData>();
        for (int i = 0; i < count; i++)
        {
            groups.Add(new GroupData(TestBase.GenerateRandomString(10))
            {
                Header = TestBase.GenerateRandomString(20),
                Footer = TestBase.GenerateRandomString(20)
            });
        }
        StreamWriter writer = new StreamWriter(filename);
        if (format == "xml")
        {
            StreamWriter writer = new StreamWriter(filename);
            WriteGroupsToXmlFile(groups, writer);

        }
        else
        {
            System.Console.Out.Write("Unrecognized format" + format);
        }
        writer.Close();
    }

    static void WriteGroupsToXmlFile(List<GroupData> groups, StreamWriter writer)
    {
        new XmlSerializer(typeof(List<GroupData>)).Serialize(writer, groups);
    }
}