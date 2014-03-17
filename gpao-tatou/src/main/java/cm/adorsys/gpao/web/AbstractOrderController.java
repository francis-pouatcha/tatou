/**
 * 
 */
package cm.adorsys.gpao.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;

/**
 * @author bwa
 *
 */
public class AbstractOrderController {

    @RequestMapping(value = "/getUdmListFromUdmGroup/{groupId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUdmListFromUdmGroup(@PathVariable("groupId") Long groupId) {
        List<UnitOfMesures> resultList = UnitOfMesures.findUnitOfMesuressByGroupEquals(UdmGroup.findUdmGroup(groupId)).getResultList();
        return UnitOfMesures.toJsonArray(resultList);
    }
}