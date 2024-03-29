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

import taller2.Palma.demo.delegate.DocStateInsDelegate;
import taller2.Palma.demo.delegate.PersonDelegate;
import taller2.Palma.demo.delegate.DocumentDelegate;
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
	
	private DocStateInsDelegate dsi;
	private PersonDelegate per;
	private DocumentDelegate doc;
	
	@Autowired
	public DocStateInsController(DocStateInsDelegate dsi,PersonDelegate per, DocumentDelegate doc) {
		this.dsi=dsi;
		this.per=per;
		this.doc=doc;
	}
	
	@GetMapping("/docinst/")
	public String indexDocIns(Model model) {
		model.addAttribute("instances",dsi.getDroupDSI());
		return "docinstance/index";
	}
	
	@GetMapping("/docinst/add")
	public String addDocIns(Model model) {
		model.addAttribute("docstateinstance",new Docstateinstance());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("docs",doc.getGroupDocuments());
		return "docinstance/addInsDoc";
	}
	
	@PostMapping("/docinst/add")
	public String saveDocIns(@RequestParam(value="action",required=true) String action, @Validated(add.class) @ModelAttribute Docstateinstance docstateinstance,
			BindingResult bind, Model model) {
		if(!action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("people",per.getGroupPersonData());
				model.addAttribute("docs",doc.getGroupDocuments());
				return "docinstance/addInsDoc";
			}
//			try {
				dsi.createDSI(docstateinstance);
//			} catch (NonValidDateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/edit/{id}")
	public String editDocIns(@PathVariable("id")long id,Model model) {
		final Optional<Docstateinstance> instance=Optional.of(dsi.getDSI(id));
		if(instance==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		model.addAttribute("docstateinstance",instance.get());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("docs",doc.getGroupDocuments());
		return "docinstance/editDocInstance";
	}
	
	@PostMapping("/docinst/edit/{id}")
	public String updateIns(@RequestParam(value="action",required=true) String action,@Validated(update.class) @ModelAttribute Docstateinstance docstateinstance,BindingResult bind,
			@PathVariable("id") long id,
			Model model) {
		if(action!=null&& !action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("docstateinstance",docstateinstance);
				model.addAttribute("people",per.getGroupPersonData());
				model.addAttribute("docs",doc.getGroupDocuments());
				return "docinstance/editDocInstance";
			}
//			try {
				docstateinstance.setDocstatinsId(id);
				dsi.updateDSI(id, docstateinstance);
//			} catch (NonValidDateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/del/{id}")
	public String deleteDocInst(@PathVariable("id") long id, Model model) {
		final Optional<Docstateinstance> docins=Optional.of(dsi.getDSI(id));
		if(docins==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		dsi.deleteDSI(docins.get().getDocstatinsId());
		model.addAttribute("instances",dsi.getDroupDSI());
		return "redirect:/docinst/";
	}
	
	@GetMapping("/docinst/view/{id}")
	public String consultDocIns(@PathVariable("id")long id,
			Model model) {
		final Optional<Docstateinstance> instance=Optional.of(dsi.getDSI(id));
		if(instance==null) {
			throw new IllegalArgumentException("Invalid Doc Instance Id: "+id);
		}
		model.addAttribute("docins",instance.get());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("docs",doc.getGroupDocuments());
		return "docinstance/consultDocIns";
	}
}
