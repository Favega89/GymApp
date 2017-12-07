package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Client;
import service.MySqlDaoClient;

@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	MySqlDaoClient clientService;
	
private Client c = new Client();
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getClient() {
		return "llego"; 
	}	
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	private @ResponseBody ArrayList<Client> getClients(){	
		ArrayList<Client> result;
		result = clientService.getAll();
		return result;
	}
	
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	@RequestMapping(value = "/unactives/", method = RequestMethod.GET)
	public @ResponseBody String getUnactives(){	
		ArrayList<Client> result;
		result = clientService.getAllUnactives();		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDateToday = new Date(utilDate.getTime());
		
		for(Client c : result){
			if(getDateDiff(c.getLastClassDate(),sqlDateToday,TimeUnit.DAYS) > 5) {
				return "true";
			};
		}
		return "false";
	}
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	/*---------------------------------------INACTIVODS / ALERTA --------------------------------------------------------*/
	
	
	@RequestMapping(value = "/all2", method = RequestMethod.GET)
	private @ResponseBody ArrayList<Client> getClients2(){	
		ArrayList<Client> result;
		result = clientService.getAll();
		return result;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	private void updateClient(@RequestBody Client client,@PathVariable String id) {
		Integer i = new Integer(id);
		clientService.modify(i,client);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	private void updateClient(@RequestBody Client client) {
		clientService.add(client);
	}
}
