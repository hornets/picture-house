/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse.views;

import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.javalite.activejdbc.Base;
import picturehouse.PictureHouse;
import picturehouse.controllers.MovieController;
import picturehouse.controllers.MovieReviewController;
import picturehouse.models.Customer;
import picturehouse.models.Movie;
import picturehouse.models.MovieReview;

/**
 *
 * @author sevabaskin
 */
public class BrowseMoviesPanel extends javax.swing.JPanel {
    private MainFrame parentFrame;
    private PictureHouse app;
    private List<Movie> lastWeekMovies;
    private List<Movie> thisAndNextWeekMovies;
    private MovieListData movieListData;
    
    /**
     * Creates new form BrowseMoviesPanel
     */
    public BrowseMoviesPanel() {
        initComponents();
    }
    public BrowseMoviesPanel(PictureHouse app, MainFrame parentFrame) {
        initComponents();
        this.app = app;
        this.parentFrame = parentFrame;
        // load necessary movies
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        MovieController controller = new MovieController();
        lastWeekMovies = controller.showLastWeekMovies();
        thisAndNextWeekMovies = controller.showThisAndNextWeekMovies();
        
        // adjust GUI and load data into it
        selectDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "This week movies", "Last week movies" }));
        selectDateComboBox.setSelectedIndex(0);
        movieListData = new MovieListData();
        movieListPane.setModel(movieListData);
        Base.close();
        // update view when a different movie is sellected
        movieListPane.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    paintCurrentlySelectedMovie();
                }
            }
        });
        repaintMovieList();
        paintCurrentlySelectedMovie();
    }

    public void paintMovie(int index) {
        Movie movie;
        if (currentWeekMoviesSelected()) {
            movie = thisAndNextWeekMovies.get(index);
        } else {
            movie = lastWeekMovies.get(index);
        }
        movieTitle.setText(movie.getString("title"));
        movieSynopsis.setText(movie.getString("synopsis"));
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        MovieReviewController controller = new MovieReviewController();
        List<MovieReview> movieReviewList = controller.getReviewsByMovieId(movie.getInteger("id"));
        String movieReviewsString = "";
        if (movieReviewList.isEmpty()) {
            movieReviewsString = "<html><body>No reviews have been written for this movie yet. If you watched this movie, you can leave a review by clicking 'Write Review' button above.</body></html>";
        } else {
            movieReviewsString += "<html><head></head><body>";
            for (MovieReview movieReview : movieReviewList) {
                movieReviewsString += String.format("<div style=\"margin: 5px 10px 30px 10px;\"><h2 class=\"header\">%s</h2><p>%s</p></div><hr>",
                                                    Customer.findFirst("id =?", movieReview.getString("customer_id")).getString("username"),
                                                    movieReview.getString("content"));
            }
            movieReviewsString += "</body></html>";
        }
        this.movieReviewsEditorPane.setText(movieReviewsString);
         
        Base.close();
    }
    
class MovieListData extends AbstractListModel {
    String[] strings;
    String[] thisAndNextWeekMovieNames;
    String[] lastWeekMovieNames;
    String noMoviesAvailableMsg = "<html>No movies available<br>during selected time<br>period. Select a<br>different period<br>in the box above</html>";
    
    public MovieListData() {
        // create array of movie names
        // handle null
        if (thisAndNextWeekMovies.isEmpty()) {
            thisAndNextWeekMovieNames = new String[1];
            thisAndNextWeekMovieNames[0] = noMoviesAvailableMsg;
            System.out.println("reached");
        } else {
            thisAndNextWeekMovieNames = new String[thisAndNextWeekMovies.size()];
            for (int i = 0; i < thisAndNextWeekMovies.size(); i++)
                thisAndNextWeekMovieNames[i] = thisAndNextWeekMovies.get(i).getString("title");
        }
        if (lastWeekMovies.isEmpty()) {
            lastWeekMovieNames = new String[1];
            lastWeekMovieNames[0] = noMoviesAvailableMsg;
        } else {
            lastWeekMovieNames = new String[lastWeekMovies.size()];
            for (int i = 0; i < lastWeekMovies.size(); i++)
                lastWeekMovieNames[i] = lastWeekMovies.get(i).getString("title");
        }        
        // load this and next week movie names into jList
        strings = thisAndNextWeekMovieNames;
    }

    public int getSize() {
        return strings.length;
    }

    public Object getElementAt(int index) {
        return strings[index];
    }
    
    public void updateWithLastWeekMovies() {
        strings = lastWeekMovieNames;
    }
    public void updateWithThisAndNextWeekMovies() {
        strings = thisAndNextWeekMovieNames;
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        movieListPane = new javax.swing.JList();
        goBackButton = new javax.swing.JButton();
        selectDateComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        movieTitle = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        movieSynopsis = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        movieReviewsEditorPane = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 547));

        movieListPane.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(movieListPane);

        goBackButton.setText("< Return to Main Screen");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        selectDateComboBox.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        selectDateComboBox.setFocusable(false);
        selectDateComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectDateComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goBackButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(selectDateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        movieTitle.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        movieTitle.setText("Good Will Hunting");

        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jButton3.setText("Book Now");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Synopsis");

        movieSynopsis.setEditable(false);
        movieSynopsis.setBackground(java.awt.SystemColor.window);
        movieSynopsis.setColumns(20);
        movieSynopsis.setRows(5);
        jScrollPane2.setViewportView(movieSynopsis);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Customer Reviews");

        movieReviewsEditorPane.setEditable(false);
        movieReviewsEditorPane.setBackground(java.awt.SystemColor.window);
        movieReviewsEditorPane.setContentType("text/html"); // NOI18N
        movieReviewsEditorPane.setText("<html>\n<head>\n<head>\n</head>\n<body style=\"font-family:Lucida grande\">\n<div style=\"margin: 5px 10px 30px 10px;\">\n<h2 class=\"header\">armand_baboian</h2>\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\n</div>\n<hr>\n<div style=\"margin: 5px 10px 30px 10px;\">\n<p class=\"header\">akshay93</p>\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\n</div>\n</body>\n</html>");
        movieReviewsEditorPane.setToolTipText("");
        movieReviewsEditorPane.setHighlighter(null);
        movieReviewsEditorPane.setMaximumSize(new java.awt.Dimension(1000, 1000));
        movieReviewsEditorPane.setMinimumSize(new java.awt.Dimension(130, 400));
        jScrollPane3.setViewportView(movieReviewsEditorPane);

        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jButton1.setText("Write review");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(movieTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                .addComponent(jButton3)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieTitle)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        this.parentFrame.showCard("homePageCard");
    }//GEN-LAST:event_goBackButtonActionPerformed

    private void selectDateComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectDateComboBoxItemStateChanged
        if( evt.getStateChange() == ItemEvent.SELECTED ) {
            if (currentWeekMoviesSelected()) {
                movieListData.updateWithThisAndNextWeekMovies();
            } else if (lastWeekMoviesSelected()) {
                movieListData.updateWithLastWeekMovies();
            }
            repaintMovieList();
        }
    }//GEN-LAST:event_selectDateComboBoxItemStateChanged

    private void repaintMovieList() {
        movieListPane.setSelectedIndex(0);
        movieListPane.repaint();
        paintCurrentlySelectedMovie();
    }
    
    private boolean currentWeekMoviesSelected() {
        return this.selectDateComboBox.getSelectedIndex() == 0;
    }
    private boolean lastWeekMoviesSelected() {
        return this.selectDateComboBox.getSelectedIndex() == 1;    
    }
    private void paintCurrentlySelectedMovie(){
        paintMovie(movieListPane.getSelectedIndex());
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList movieListPane;
    private javax.swing.JEditorPane movieReviewsEditorPane;
    private javax.swing.JTextArea movieSynopsis;
    private javax.swing.JLabel movieTitle;
    private javax.swing.JComboBox selectDateComboBox;
    // End of variables declaration//GEN-END:variables
}
