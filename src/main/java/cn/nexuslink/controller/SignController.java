package cn.nexuslink.controller;

import cn.nexuslink.pojo.Member;
import cn.nexuslink.pojo.SignMembers;
import cn.nexuslink.pojo.Signment;
import cn.nexuslink.pojo.constant.StatusCode;
import cn.nexuslink.pojo.json.ResponseJson;
import cn.nexuslink.service.impl.MemberService;
import cn.nexuslink.service.impl.SignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

import static cn.nexuslink.pojo.constant.StatusCode.INTERNAL_SERVER_ERROR;
import static cn.nexuslink.pojo.constant.StatusCode.INVALID_REQUEST;
import static cn.nexuslink.pojo.constant.StatusCode.OK;

/**
 * Created by 罗浩 on 2017/4/27.
 */
@Controller
@RequestMapping(value = "/api/sign")
public class SignController {

    @Resource
    private MemberService memberService;

    @Resource
    private SignmentService signmentService;

    @RequestMapping(value = "/getSignments")
    @ResponseBody
    public ResponseJson getSignments(){
        List<Member> members = memberService.getMembers(1);
        List<Signment> signments = signmentService.getSignments();
        if(members!=null && signments!=null){
            SignMembers smb = new SignMembers(members, signments);
            return new ResponseJson(OK.getCode(),StatusCode.OK.getMessage(),smb);
        }
        return new ResponseJson(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage(),"服务器无法取得数据库数据！");
    }

    @RequestMapping(value = "/getSignInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getSignInfo(@RequestParam("realname") String realname){
        Signment sm = signmentService.getSignInfoByName(realname);
        if(null!=sm)
            return new ResponseJson(OK.getCode(), OK.getMessage(), sm);
        return new ResponseJson(StatusCode.INVALID_REQUEST.getCode(), INVALID_REQUEST.getMessage(),"研究中心不存在该成员！");
    }
}
