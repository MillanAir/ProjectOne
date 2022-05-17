/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.revature;

import com.revature.controllers.ReimController;
import com.revature.controllers.UserController;
import com.revature.dao.IReimDao;
import com.revature.dao.IUserDao;
import com.revature.dao.ReimDaoJDBC;
import com.revature.dao.UserDaoJDBC;
import com.revature.services.ReimService;
import com.revature.services.UserService;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        IUserDao uDao = new UserDaoJDBC();
        IReimDao rDao = new ReimDaoJDBC();

        UserService uService = new UserService(uDao);
        ReimService rService = new ReimService(rDao);

        UserController uController = new UserController(uService);
        ReimController rController = new ReimController(rService);


        try{
            Javalin server = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

            server.routes(()-> {
                path("employee", () -> {
                    post("/login", uController.handleLogin);
                    put("/update", uController.handleUpdate);
                    put("/request", rController.handleCreateReim);
                    get("/reimbursements", rController.handleViewReim);
                });
                path("manager", () -> {
                    get("/reimbursements", rController.handleViewPendingReim);
                    put("/deny", rController.handleDeny);
                    put("/approve", rController.handleApprove);
                });
                path("logout", () -> {
                    get("/", uController.handleLogout);
                });
            });

            server.start(8080);
        } catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            logger.info("#2. Connection succeeded!");
        }
    }
}
