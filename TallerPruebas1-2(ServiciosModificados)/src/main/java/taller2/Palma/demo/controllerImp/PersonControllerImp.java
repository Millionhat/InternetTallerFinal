package taller2.Palma.demo.controllerImp;

import java.util.Optional;

import org.h2.command.dml.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import taller2.Palma.demo.delegate.DocumentDelegate;
import taller2.Palma.demo.delegate.IdDocTypeDelegate;
import taller2.Palma.demo.delegate.InstitutionDelegate;
import taller2.Palma.demo.delegate.PersonDelegate;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.model.add;
import taller2.Palma.demo.model.update;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.IdDocTypeService;
import taller2.Palma.demo.service.InstitutionService;
import taller2.Palma.demo.service.PersonService;


@Controller
public class PersonControllerImp {
	
	private InstitutionDelegate ins;
	private PersonDelegate per;
	private DocumentDelegate doc;
	private IdDocTypeDelegate idts;
	
	@Autowired
	public PersonControllerImp(InstitutionDelegate serv, PersonDelegate ser, DocumentDelegate doc, IdDocTypeDelegate idc) {
		ins=serv;
		per=ser;
		this.doc=doc;
		idts=idc;
	}
	
	@GetMapping("/person/")
	public String indexPerson(Model model) {
		model.addAttribute("people", per.getGroupPersonData());
		return "person/index";
	}
	
	@GetMapping("/person/add")
	public String addPer(Model model) {
		model.addAttribute("person",new Person());
		model.addAttribute("institutions",ins.getGroupInstitution());
		model.addAttribute("types",idts.getGroupTypes());
		return "person/addPerson";
	}
	
	@PostMapping("/person/add")
	public String savePerson(@RequestParam(value="action",required=true)
			String action, @Validated(add.class) @ModelAttribute Person person, BindingResult bind,
			Model model) {
		if(!action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("institutions",ins.getGroupInstitution());
				model.addAttribute("types",idts.getGroupTypes());
				return "person/addPerson";
			}
//			try {
				per.createPerson(person);
//			} catch (NonNullValueException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return "redirect:/person/";
	}
	
	@GetMapping("/person/edit/{id}")
	public String editInfo(@PathVariable("id") long id, Model model) {
		final Optional<Person> person=Optional.of(per.getPerson(id));
		if(person== null) {
			throw new IllegalArgumentException("INVALID iD "+id);
		}
		model.addAttribute("person",person.get());
		model.addAttribute("institutions",ins.getGroupInstitution());
		model.addAttribute("types",idts.getGroupTypes());
		return "person/editPerson";
	}
	
	@PostMapping("person/edit/{id}")
	public String updatePerson(@Validated(update.class) @ModelAttribute Person person,
			BindingResult bind,@PathVariable("id") long id, @RequestParam(value="action",
			required=true) String action, Model model) {
		if(action!=null && !action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("person",person);
				model.addAttribute("institutions",ins.getGroupInstitution());
				model.addAttribute("types",idts.getGroupTypes());
				return "person/editPerson";
			}
//			try {
				person.setPersId(id);
				person.setPersIddocument(per.getPerson(id).getPersIddocument());
				per.updatePerson(id,person);
//			} catch (NonNullValueException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			model.addAttribute("people",per.getGroupPersonData());
		}
		return "redirect:/person/";
	}
	
	@GetMapping("/person/del/{id}")
	public String deletePerson(@PathVariable("id") long id, Model model) {
		final Optional<Person> perse=Optional.of(per.getPerson(id));
		if(perse==null) {
			throw new IllegalArgumentException("Invalid Doc Type Id: "+id);
		}
		per.deletePerson(id);;
		model.addAttribute("poeple",per.getGroupPersonData());
		return "redirect:/person/";
	}
	
	@GetMapping("/person/view/{id}")
	public String consultInfo(@PathVariable("id") long id, Model model) {
		final Optional<Person> person=Optional.of(per.getPerson(id));
		if(person== null) {
			throw new IllegalArgumentException("INVALID iD "+id);
		}
		model.addAttribute("person",person.get());
		model.addAttribute("institutions",ins.getGroupInstitution());
		model.addAttribute("types",idts.getGroupTypes());
		model.addAttribute("documents",doc.getGroupDocByPerson(person.get()));
		return "person/consultPerson";
	}
}
