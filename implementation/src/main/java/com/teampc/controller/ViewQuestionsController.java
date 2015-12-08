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

   private static final String RESOURCE = "preview-short-question-row.fxml";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      data = FXCollections.observableArrayList();

      List<Question> questionList = QuestionDAO.getInstance().fetchAll();
      questionList.forEach(question -> {
         Optional<QuestionPreviewController> controller = QuestionPreviewFactory.getQuestionController(question);
         controller.ifPresent(c -> data.add(new RowView<>(c, RESOURCE)));
      });

      initView();
   }
}
