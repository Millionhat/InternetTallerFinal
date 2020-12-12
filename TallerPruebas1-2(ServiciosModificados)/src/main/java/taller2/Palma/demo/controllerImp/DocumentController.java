package taller2.Palma.demo.controllerImp;

import java.util.Optional;

import org.h2.command.dml.Update;
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
import taller2.Palma.demo.delegate.DocumentDelegate;
import taller2.Palma.demo.delegate.DocumentTypeDelegate;
import taller2.Palma.demo.delegate.PersonDelegate;
import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Person;
import taller2.Palma.demo.model.add;
import taller2.Palma.demo.model.update;
import taller2.Palma.demo.service.DocStateInstanceService;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentTypeService;
import taller2.Palma.demo.service.PersonService;

@Controller
public class DocumentController {
	
	private DocumentDelegate docs;
	private PersonDelegate per;
	private DocumentTypeDelegate dts;
	private DocStateInsDelegate dsi;
	
	public DocumentController(DocumentDelegate doc,PersonDelegate per,DocumentTypeDelegate dts, DocStateInsDelegate dsi) {
		docs=doc;
		this.per=per;
		this.dts=dts;
		this.dsi=dsi;
	}
	
	@GetMapping("/docs/")
	public String indexPerson(Model model) {
		model.addAttribute("documents",docs.getGroupDocuments());
		return "document/index";
	}
	
	@GetMapping("/docs/add")
	public String addDoc(Model model) {
		model.addAttribute("documentt",new Documentt());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("types",dts.getGroupDocTypeData());
		return "document/addDoc";
	}
	
	@PostMapping("/docs/add")
	public String saveDoc(@RequestParam(value="action",required=true)
			String action, @Validated(add.class) @ModelAttribute Documentt documentt, BindingResult bind,
			Model model) {
		if(!action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("people",per.getGroupPersonData());
				model.addAttribute("types",dts.getGroupDocTypeData());
				return "document/addDoc";
			}
			docs.createDocument(documentt);
		}
		return "redirect:/docs/";
	}
	
	@GetMapping("/docs/edit/{id}")
	public String editInfo(@PathVariable("id") long id, Model model) {
		final Optional<Documentt> docu=Optional.of(docs.getDocument(id));
		if(docu== null) {
			throw new IllegalArgumentException("INVALID iD "+id);
		}
		model.addAttribute("documentt",docu.get());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("types",dts.getGroupDocTypeData());
		return "document/editDoc";
	}
	
	@PostMapping("docs/edit/{id}")
	public String updateDoc(@Validated(update.class) @ModelAttribute Documentt documentt,
			BindingResult bind,@PathVariable("id") long id, @RequestParam(value="action",
			required=true) String action, Model model) {
		if(action!=null && !action.equals("Cancel")) {
			if(bind.hasErrors()) {
				model.addAttribute("documentt",documentt);
				model.addAttribute("people",per.getGroupPersonData());
				model.addAttribute("types",dts.getGroupDocTypeData());
				return "document/editDoc";
			}
			documentt.setDocId(id);
			docs.updateDoc(id, documentt);
			model.addAttribute("docs",docs.getGroupDocuments());
		}
		return "redirect:/docs/";
	}
	
	@GetMapping("/docs/del/{id}")
	public String deletePerson(@PathVariable("id") long id, Model model) {
		final Optional<Documentt> docu=Optional.of(docs.getDocument(id));
		if(docu==null) {
			throw new IllegalArgumentException("Invalid Doc Type Id: "+id);
		}
		docs.deleteDoc(id);;
		model.addAttribute("docs",docs.getGroupDocuments());
		return "redirect:/docs/";
	}
	
	@GetMapping("/docs/view/{id}")
	public String comsultInfo(@PathVariable("id") long id, Model model) {
		final Optional<Documentt> docu=Optional.of(docs.getDocument(id));
		if(docu== null) {
			throw new IllegalArgumentException("INVALID iD "+id);
		}
		model.addAttribute("doc",docu.get());
		model.addAttribute("people",per.getGroupPersonData());
		model.addAttribute("types",dts.getGroupDocTypeData());
		model.addAttribute("instances",dsi.getDSIDoc(docu.get()));
		return "document/consultDoc";
	}
}
