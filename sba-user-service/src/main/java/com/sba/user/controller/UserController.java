package com.sba.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sba.security.TokenProvider;
import com.sba.security.util.Translator;
import com.sba.user.entity.User;
import com.sba.user.exception.PaginationSortingException;
import com.sba.user.model.AuthToken;
import com.sba.user.model.UserDtls;
import com.sba.user.pagination.Direction;
import com.sba.user.pagination.OrderBy;
import com.sba.user.repo.UserRepository;
import com.sba.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Api(value = "user-service", description = "Operations pertaining to user services")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	@GetMapping("/gettingUsers")
	public List<User> getUsers()
	{
		
		return userRepo.findAll();
	
		
	}
	
	@ApiOperation(value = "Signin user", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 401, message = "Unauthorized User"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})	
	@PostMapping("/signin")
	public com.sba.security.model.ApiResponse<?> signin(
			@ApiParam(value = "User details containing user id and password", required = true) @RequestBody User loginUser) {
		
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
		User user = userService.findByName(loginUser.getUserName());
		
		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.user.signin"),
				new AuthToken(user.getId(), user.getFirstName(), authentication.getAuthorities().toString(), token));

	}

	
	@ApiOperation(value = "Signup user details and send mail to active account", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 302, message = "Resource Exist"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 412, message = "Invalid Sort Direction"),
		@ApiResponse(code = 500, message = "Data Validation Error"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})	
	@PostMapping("/signup")
	public com.sba.security.model.ApiResponse<?> signup(
			@ApiParam(value = "User details which needs to signup", required = true) @Valid @RequestBody User user,
			HttpServletRequest request) {

		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.user.signup"), userService.signup(user, request));

	}

	@ApiOperation(value = "Validate user and send mail to reset password", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@GetMapping("/forgetPassword/{userName}")
	public com.sba.security.model.ApiResponse<?> forgetPassword(
			@ApiParam(value = "User name for which password need to reset", required = true) @PathVariable(value = "userName", required = true) String userName,
			HttpServletRequest request) {
		
        userService.forgetPassword(userName, request);		
		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.user.resetpwd"));
	}
	
	@ApiOperation(value = "Block/Unblock am user", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/blockUnblockUser/{id}/{block}")
	public com.sba.security.model.ApiResponse<?> blockUnblockUser(
			@ApiParam(value = "User Id for which user needs to block/unblock", required = true) @PathVariable(value = "id", required = true) Long id,
			@ApiParam(value = "Value for block/unblock. block = true and unblock = false", required = true) @PathVariable(value = "block", required = true) Boolean block) {

		User user = new User();
		user.setId(id);
		user.setActive(block);
	
		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(),
				Translator.toLocale("success.user.block-unblock", (block.booleanValue() == false ? "blocked" : "unblocked")),
				userService.updateProfile(user));

	}

	@ApiOperation(value = "Update user details", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MENTOR')")
	@PutMapping("/updateProfile")
	public com.sba.security.model.ApiResponse<?> updateProfile(
			@ApiParam(value = "User details which needs to update", required = true) @Valid @RequestBody User user) {

		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.user.update"),
				userService.updateProfile(user));

	}

	@ApiOperation(value = "Delete user details", response = com.sba.security.model.ApiResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteProfile/{id}")
	public com.sba.security.model.ApiResponse<?> deleteProfile(
			@ApiParam(value = "User Id for which record needs to delete", required = true) @PathVariable(value = "id", required = true) Long id) {

		userService.deleteProfile(id);
		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), Translator.toLocale("success.user.delete"));
	}

	@ApiOperation(value = "View all registered users", response = Page.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAllUsers")
	public Page<User> findAllUsers(
			@ApiParam(value = "Ordring By search rerods", required = true) @RequestParam(value = "orderBy", required = true) String orderBy,
			@ApiParam(value = "Ordring search rerods (ASC/DESC)", required = true) @RequestParam(value = "direction", required = true) String direction,
			@ApiParam(value = "Offset value for pagination", required = true) @RequestParam(value = "page", required = true) int page,
			@ApiParam(value = "Per page record size in pagination", required = true) @RequestParam(value = "size", required = true) int size) {

		if (!(direction.equals(Direction.ASCENDING.getDirectionCode())
				|| direction.equals(Direction.DESCENDING.getDirectionCode()))) {
			throw new PaginationSortingException(Translator.toLocale("error.invalid.order"));
		}
		if (!(orderBy.equals(OrderBy.ID.getOrderByCode()) || orderBy.equals(OrderBy.USERID.getOrderByCode()))) {
			throw new PaginationSortingException(Translator.toLocale("error.invalid.orderby"));
		}
		
		return userService.findAllUsers(orderBy, direction, page, size);		
	}

	@ApiOperation(value = "Find user details for specific user id", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@GetMapping("/findById/{id}")
	public User findById(
			@ApiParam(value = "user Id for which record needs to find", required = true) @PathVariable(value = "id", required = true) Long id) {
		
		return userService.findById(id);
	}

	@ApiOperation(value = "Find user details for specific user name", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 404, message = "Resource Not Found"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@GetMapping("/findByName/{userName}")
	public User findByName(
			@ApiParam(value = "User Name for which record needs to find", required = true) @PathVariable(value = "userName", required = true) String userName) {
		
		return userService.findByName(userName);
		
	}
	
	
	
	//below two rest service called from jsp pages through ajax
	
	@ApiOperation(value = "Confirm User details", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})	
	@PostMapping(value = "/confirmUser")
	public String confirmUser(
			@ApiParam(value = "User details containing user id and confirmation token", required = true) @RequestBody UserDtls user,
			HttpServletRequest request) throws Exception {

		return userService.confirmUser(user);
	}
	
	@ApiOperation(value = "Reset password details", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully performed the operation"),
		@ApiResponse(code = 503, message = "Service Unavailable")
	})
	@PostMapping(value = "/resetPassword")
	public String resetPassword(
			@ApiParam(value = "User details containing user id, new password and reset-password token", required = true) @RequestBody UserDtls user, 
			HttpServletRequest request) throws Exception {

		return userService.resetPassword(user);
	}
}