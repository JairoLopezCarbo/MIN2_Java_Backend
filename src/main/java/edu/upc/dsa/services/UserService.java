package edu.upc.dsa.services;

import edu.upc.dsa.SystemManager;
import edu.upc.dsa.services.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/user", description = "Minimo2 team endpoints")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    Logger logger = Logger.getLogger(UserService.class);

    @GET
    @Path("/{username}/team")
    @ApiOperation(value = "Get fish species by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Fish.class),
            @ApiResponse(code = 404, message = "Species not found")
    })
    public Response getFish(@PathParam("username") String username) {
        //Dictionary teams
        logger.info("Getting team of user: " + username);
        List<Team> teams = new ArrayList<>();
        List<TeamMember> porxinosMembers = new ArrayList<>();
        porxinosMembers.add(new TeamMember("Juan", "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png", 250));
        porxinosMembers.add(new TeamMember("Palomo", "https://cdn.pixabay.com/photo/2016/01/10/18/59/cookie-monster-1132275_1280.jpg", 200));
        teams.add(new Team("porxinos", porxinosMembers));

        List<TeamMember> algasMembers = new ArrayList<>();
        algasMembers.add(new TeamMember("Jose", "https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397_1280.png", 150));
        algasMembers.add(new TeamMember("Maria", "https://cdn.pixabay.com/photo/2016/01/10/18/59/cookie-monster-1132275_1280.jpg", 100));
        teams.add(new Team("algas", algasMembers));

        for (Team t : teams) {
            for (TeamMember m : t.getMembers()) {
                if (m.getName().equalsIgnoreCase(username)) {
                    logger.info(t.toString());
                    return Response.ok(t).build();
                }
            }

        }
        Team emptyTeam = new Team();
        logger.info("User not found: " + username);
        return Response.ok(emptyTeam).build();

    }



}
