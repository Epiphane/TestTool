package com.teampc.controller;

import com.teampc.controller.questionpreview.QuestionPreviewFactory;
import com.teampc.controller.questionpreview.QuestionPreviewController;
import com.teampc.dao.QuestionDAO;
import com.teampc.model.question.Question;
import com.teampc.view.RowView;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewQuestionsController extends ListViewController<RowView> {

   private static final String RESOURCE = "view-question-row.fxml";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.emptyObservableList();

      List<Question> questionList = QuestionDAO.getInstance().fetchAll();
      questionList.forEach(question -> {
         Optional<QuestionPreviewController> controller = QuestionPreviewFactory.getQuestionController(question);
         if(controller.isPresent()) {
            data.add(new RowView(controller.get(), RESOURCE));
         }
      });

      super.initialize(location, resources);
   }

}
