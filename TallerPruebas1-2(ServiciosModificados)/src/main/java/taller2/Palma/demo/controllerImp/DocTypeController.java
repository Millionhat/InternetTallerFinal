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

import taller2.Palma.demo.exception.NonNullValueException;
import taller2.Palma.demo.model.Documentt;
import taller2.Palma.demo.model.Documenttype;
import taller2.Palma.demo.model.add;
import taller2.Palma.demo.model.update;
import taller2.Palma.demo.service.DocumentService;
import taller2.Palma.demo.service.DocumentTypeService;
import taller2.Palma.demo.service.InstitutionService;

@Controller
public class DocTypeController {
	
	private InstitutionService ins;
	private DocumentTypeService dct;
	private DocumentService docs;
	@Autowired
	public DocTypeController(InstitutionService serv, DocumentTypeService ser,DocumentService doc) {
		ins=serv;
		dct=ser;
		docs=doc;
	}
	
	@GetMapping("/docType/")
	public String indexDocType(Model model) {
		model.addAttribute("docTypes",dct.getDocTypes());
		return "docType/index";
	}
	
	@GetMapping("/docType/add")
	public String addDocType(Model model) {
		model.addAttribute("docType",new Documenttype());
		model.addAttribute("institution",ins.getInstitutions());
		return "docType/addDocType";
	}
	
	@PostMapping("/docType/add")
	public String saveDocType(@RequestParam(value="action",required=true) String action,
			@Validated(add.class) @ModelAttribute Documenttype documenttype,BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("institution",ins.getInstitutions());
				return "docType/addDocType";
			}
			try {
				dct.addDT(documenttype);
			} catch (NonNullValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/docType/";
	}
	
	@GetMapping("/docType/edit/{id}")
	public String editDocType(@PathVariable("id") long id, Model model) {
		final Optional<Documenttype> docType=dct.getDocType(id);//Sino toca dejarlo en optional
		if(docType==null) {
			throw new IllegalArgumentException("INVALID ID "+id);
		}
		model.addAttribute("docType",docType.get());
		model.addAttribute("institutions",ins.getInstitutions());
		return "docType/editDocType";
	}
	
	@PostMapping("/docType/edit/{id}")
	public String updateDocType(@PathVariable("id") long id, @RequestParam(value="action", required=true) String action,
			@Validated(update.class) @ModelAttribute Documenttype documenttype,BindingResult bind, Model model) {
		if(action!=null && !action.equals("Cancel")){
			if(bind.hasErrors()) {
				model.addAttribute("docType",documenttype);
				model.addAttribute("institutions",ins.getInstitutions());
				return "docType/editDocType";
			}
			try {
				documenttype.setDoctypeId(id);
				dct.addDT(documenttype);
			} catch (NonNullValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("docTypes",dct.getDocTypes());
		}
		return "redirect:/docType/";
	}
	
	@GetMapping("/docType/del/{id}")
	public String deleteDocType(@PathVariable("id") long id, Model model) {
		final Optional<Documenttype> docType=dct.getDocType(id);
		if(docType==null) {
			throw new IllegalArgumentException("Invalid Doc Type Id: "+id);
		}
		dct.delete(id);
		model.addAttribute("docTypes",dct.getDocTypes());
		return "redirect:/docType/";
	}
	
	@GetMapping("/docType/view/{id}")
	public String consultDocType(@PathVariable("id") long id, Model model) {
		final Optional<Documenttype> docType=dct.getDocType(id);//Sino toca dejarlo en optional
		if(docType==null) {
			throw new IllegalArgumentException("INVALID ID "+id);
		}
		model.addAttribute("docType",docType.get());
		model.addAttribute("institutions",ins.getInstitutions());
		model.addAttribute("documents",docs.findDocsByType(docType.get()));
		return "docType/consultDocType";
	}
}
