import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;

public class NaiveBayesClassifier {
    public static void main(String[] args) throws Exception {
        // Load dataset
        DataSource trainSource = new DataSource("C:\\Users\\tonga\\IdeaProjects\\DataMining-Project\\Data\\training_data.arff");
        Instances trainDataset = trainSource.getDataSet();
        DataSource testSource = new DataSource("C:\\Users\\tonga\\IdeaProjects\\DataMining-Project\\Data\\testing_data.arff");
        Instances testDataset = testSource.getDataSet();

        trainDataset.setClassIndex(trainDataset.numAttributes() - 1);
        testDataset.setClassIndex(testDataset.numAttributes() - 1);

        // Create and build the classifier
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(trainDataset);

        System.out.println("NB params" + String.join(" ", nb.getOptions()));

        Evaluation eval = new Evaluation(trainDataset);
        eval.evaluateModel(nb, testDataset);

        // Print the confusion matrix
        System.out.println("Confusion Matrix:\n" + eval.toMatrixString());

        // Print additional evaluation metrics
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        System.out.println("Precision = " + eval.precision(1));
        System.out.println("Recall = " + eval.recall(1));
        System.out.println("F-Measure = " + eval.fMeasure(1));
        System.out.println("Error Rate = " + eval.errorRate());
        System.out.println(eval.toClassDetailsString());

        weka.core.SerializationHelper.write("C:\\Users\\tonga\\IdeaProjects\\DataMining - Copy\\src\\BayesClassifier\\Naive_Bayes.model", nb);
        weka.core.SerializationHelper.read("C:\\Users\\tonga\\IdeaProjects\\DataMining - Copy\\src\\BayesClassifier\\Naive_Bayes.model");
    }
}
