package taller2.Palma.demo.controllerImp;

import java.util.Optional;

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

import taller2.Palma.demo.exception.NonValidDateException;
import taller2.Palma.demo.model.Docstateinstance;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.add;
import taller2.Palma.demo.model.update;
import taller2.Palma.demo.service.DocStateInstanceService;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.PersonService;

@Controller
public class DocStateInsController {
	
	private DocStateInstanceService dsi;
	private PersonService per;
	private DocumentService doc;
	
	@Autowired
	public DocStateInsController(DocStateInstanceService dsi,PersonService per, DocumentService doc) {
		this.dsi=dsi;
		this.per=per;
		this.doc=doc;
	}
	
	@GetMapping("/docinst/")
	public String indexDocIns(Model model) {
		model.addAttribute("instances",dsi.getDocInstances());
		return "docinstance/index";
	}
	
	@GetMapping("/docinst/add")
	public String addDocIns(Model model) {
		model.addAttribute("docstateinstance",new Docstateinstance());
		model.addAttribute("people",per.getPeople());
		model.addAttribute("docs",doc.getDocs());
		return "docinstance/addInsDoc";
	}
	
	@PostMapping("/docinst/add")
	public String saveDocIns(@RequestParam(value="action",required=true) String action, @Validated(add.class) @ModelAttribute Docstateinstance docstateinstance,
			BindingResult bind, Model model) {
		if(!action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("people",per.getPeople());
				model.addAttribute("docs",doc.getDocs());
				return "docinstance/addInsDoc";
			}
			try {
				dsi.addDTS(docstateinstance);
			} catch (NonValidDateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/edit/{id}")
	public String editDocIns(@PathVariable("id")long id,Model model) {
		final Optional<Docstateinstance> instance=dsi.getDocInstance(id);
		if(instance==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		model.addAttribute("docstateinstance",instance.get());
		model.addAttribute("people",per.getPeople());
		model.addAttribute("docs",doc.getDocs());
		return "docinstance/editDocInstance";
	}
	
	@PostMapping("/docinst/edit/{id}")
	public String updateIns(@RequestParam(value="action",required=true) String action,@Validated(update.class) @ModelAttribute Docstateinstance docstateinstance,BindingResult bind,
			@PathVariable("id") long id,
			Model model) {
		if(action!=null&& !action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("docstateinstance",docstateinstance);
				model.addAttribute("people",per.getPeople());
				model.addAttribute("docs",doc.getDocs());
				return "docinstance/editDocInstance";
			}
			try {
				docstateinstance.setDocstatinsId(id);
				dsi.addDTS(docstateinstance);
			} catch (NonValidDateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/del/{id}")
	public String deleteDocInst(@PathVariable("id") long id, Model model) {
		final Optional<Docstateinstance> docins=dsi.getDocInstance(id);
		if(docins==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		dsi.delete(docins.get());
		model.addAttribute("instances",dsi.getDocInstances());
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/view/{id}")
	public String consultDocIns(@PathVariable("id")long id,
			Model model) {
		final Optional<Docstateinstance> instance=dsi.getDocInstance(id);
		if(instance==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		model.addAttribute("docins",instance.get());
		model.addAttribute("people",per.getPeople());
		model.addAttribute("docs",doc.getDocs());
		return "docinstance/consultDocIns";
	}
}
