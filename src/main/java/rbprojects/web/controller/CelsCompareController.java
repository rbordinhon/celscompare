package rbprojects.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rbprojects.web.vo.CelularInfoVO;

@RestController
public class CelsCompareController {

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CelularInfoVO> getInfos() {
		List<CelularInfoVO> infos = new ArrayList();
		CelularInfoVO info = new CelularInfoVO();
		info.name = "infod";
		infos.add(info);
		info = new CelularInfoVO();
		info.name = "infod2";
		infos.add(info);
		return infos;

	}

}
