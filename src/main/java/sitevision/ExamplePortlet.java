package sitevision;

import senselogic.sitevision.api.Utils;
import senselogic.sitevision.api.context.PortletContextUtil;
import senselogic.sitevision.api.metadata.MetadataUtil;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author khoi.nguyen
 */

public class ExamplePortlet extends GenericPortlet {
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        Utils utils = (Utils) request.getAttribute("sitevision.utils");
        PortletContextUtil pcUtil = utils.getPortletContextUtil();
        MetadataUtil metaUtil = utils.getMetadataUtil();

        Node currentPage = utils.getPortletContextUtil().getCurrentPage();
        String categoryList = "";
        try {
            Node newsNode = currentPage.getNode("News");

            Property category = newsNode.getProperty("category");
            if (category != null) {
                Value[] values = category.getValues();
                for (Value v : values) {
                    categoryList += v.getString() + " ;";
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }


        PrintWriter writer = response.getWriter();
        writer.write("List of all categories: " + categoryList);
        writer.close();
    }
}
