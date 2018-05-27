package com.sky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/readinglist")
public class ReadingController {
    private Repository readinglist;
    private AmazonProperties amazonp;

    @Autowired
    public ReadingController(Repository readinglist,AmazonProperties amazonq){
        this.readinglist=readinglist;
        this.amazonp=amazonq;
    }

    @RequestMapping(value="/{reader}",method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader,Model model){
        List<Book> readinglist1=readinglist.findByReader(reader);
        if(readinglist1!=null){
            model.addAttribute("books",readinglist1);
            model.addAttribute("amazonID",amazonp.getAssoiateId());
        }
        return "readinglist";
    }

    @RequestMapping(value="/{reader}",method = RequestMethod.POST)
    public String addtoReading(@PathVariable("reader") String reader,Book book){
        book.setReader(reader);
        readinglist.save(book);
        return "redirect:/readinglist/{reader}";

    }
}
