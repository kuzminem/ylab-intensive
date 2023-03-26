package io.ylab.intensive.lesson04.movie;

public class Packer {
    public Movie getMovie(String[] fields, int linesCounter) {
        Movie movie = new Movie();
        movie.setTitle(fields[2]);

        try {
            if (fields[0].equals("")) {
                movie.setYear(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - year not found");
            } else {
                movie.setYear(Integer.parseInt(fields[0]));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[1].equals("")) {
                movie.setLength(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - length not found");
            } else {
                movie.setLength(Integer.parseInt(fields[1]));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[3].equals("")) {
                movie.setSubject(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - subject not found");
            } else {
                movie.setSubject(fields[3]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[4].equals("")) {
                movie.setActors(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - actors not found");
            } else {
                movie.setActors(fields[4]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[5].equals("")) {
                movie.setActress(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - actress not found");
            } else {
                movie.setActress(fields[5]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[6].equals("")) {
                movie.setDirector(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - director not found");
            } else {
                movie.setDirector(fields[6]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[7].equals("")) {
                movie.setPopularity(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - popularity not found");
            } else {
                movie.setPopularity(Integer.parseInt(fields[7]));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            if (fields[8].equals("")) {
                movie.setAwards(null);
                throw new IllegalArgumentException("Line " + linesCounter
                        + " " + fields[2] + " - awards not found");
            } else {
                movie.setAwards(fields[8].equals("Yes"));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        return movie;
    }
}
