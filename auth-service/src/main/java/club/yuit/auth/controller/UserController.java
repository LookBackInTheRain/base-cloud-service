package club.yuit.auth.controller;

import club.yuit.auth.entity.User;
import club.yuit.auth.entity.input.user.ModifyUserInput;
import club.yuit.auth.entity.input.user.ModifyUserPswInput;
import club.yuit.auth.entity.input.user.UserAuthorizationInput;
import club.yuit.auth.service.UserService;
import club.yuit.common.response.BaseResponse;
import club.yuit.common.response.PageAndSortResponse;
import club.yuit.common.response.SimpleResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static club.yuit.common.response.HttpResponseUtils.successSimpleResponse;

/**
 * @author yuit
 * @date 2019/5/15 15:28
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private DefaultTokenServices tokenServices;

    public UserController(UserService userService, DefaultTokenServices tokenServices) {
        this.userService = userService;
        this.tokenServices = tokenServices;
    }


    /**
     * 获取当前登录用户
     * @param request
     * @return {@link club.yuit.common.response.SimpleResponse}
     */
    @GetMapping("/current")
    public SimpleResponse currentUser(HttpServletRequest request){

        String headerAuth= request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = headerAuth.substring(6).trim();

        OAuth2Authentication auth2Authentication= this.tokenServices.loadAuthentication(token);

        Object principal=auth2Authentication.getUserAuthentication().getPrincipal();
        String username = null;

        if(principal instanceof String){
            username = (String) principal;
        }else if (principal instanceof User){
            username = ((User) principal).getUsername();
        }

       User user= userService.findUserByUsername(username);

        Map<String,Object> details= new HashMap<>();

        details.put("username",username);
        details.put("email",user.getEmail());
        details.put("gender",user.getGender());
        details.put("fullName",user.getFullName());
        details.put("age",user.getAge());
        details.put("description",user.getDescription());

        return successSimpleResponse(details);
    }




    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String")
    public BaseResponse userDelete(@PathVariable String id,@RequestParam String currentKey) {
        return userService.userDelete(id,currentKey);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除用户", notes = "根据ids批量删除用户")
    @ApiImplicitParam(name = "ids", value = "要删除的id字符串数组", dataType = "List<String>", example = "121,122,123")
    public BaseResponse deleteByIds(@RequestBody List<String> ids,@RequestParam String currentKey) {
        return userService.userDeleteByIds(ids,currentKey);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户", notes = "根据id获取单个用户")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String")
    public SimpleResponse getUser(@PathVariable String id) {
        return userService.getUser(id);
    }


    @PostMapping()
    @ApiOperation(value = "添加用户", notes = "添加单个用户信息")
    @ApiImplicitParam(name = "userInput", value = "添加的用户信息", dataType = "User")
    public BaseResponse userAdd(@RequestBody User userInput) {
        return userService.addUser(userInput);
    }

    @PutMapping()
    @ApiOperation(value = "修改用户", notes = "修改单个用户信息")
    @ApiImplicitParam(name = "userInput", value = "修改的用户信息", dataType = "ModifyUserInput")
    public SimpleResponse userUpdate(@RequestBody ModifyUserInput userInput) throws Exception {
        return userService.userUpdate(userInput);
    }

    @PutMapping("/psw")
    @ApiOperation(value = "修改用户密码和邮件信息", notes = "修改用户密码和邮件信息")
    @ApiImplicitParam(name = "userInput", value = "修改的用户信息", dataType = "ModifyUserPswInput")
    public BaseResponse userUpdateAndPsw(@RequestBody ModifyUserPswInput userInput) throws Exception {
        return userService.userPswUpdate(userInput);
    }

    @GetMapping("/search/{username}/{currentPage}/{pageSize}")
    @ApiOperation(value = "搜索用户", notes = "根据name搜索用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "当前页数", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据数量", dataType = "Integer")
    })
    public PageAndSortResponse searchUserByUsername(@PathVariable String username, @PathVariable Integer currentPage, @PathVariable Integer pageSize) throws Exception {
        return userService.searchUserByUsername(username, currentPage, pageSize);
    }


    @GetMapping("/page/{currentPage}/{pageSize}")
    @ApiOperation(value = "分页返回用户信息", notes = "分页返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页数", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据数量", dataType = "Integer")
    })
    public PageAndSortResponse getUserPageMessage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return userService.getUserPageMessage(currentPage, pageSize);
    }


    @PostMapping("/auths")
    @ApiOperation("用户授权")
    public BaseResponse authorizationToUser(@Valid @RequestBody UserAuthorizationInput input) {
        return this.userService.authorizationToUser(input);
    }


    @GetMapping("/current/{key}")
    @ApiOperation("当前用户信息")
    public SimpleResponse currentUser(@PathVariable String key){
        return  userService.findCurrentUserById(key);
    }


}
