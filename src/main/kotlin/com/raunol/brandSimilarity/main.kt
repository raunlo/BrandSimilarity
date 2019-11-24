package com.raunol.brandSimilarity

import org.opencv.core.*
import org.opencv.features2d.ORB.*
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.features2d.DescriptorMatcher
import org.opencv.features2d.DescriptorMatcher.BRUTEFORCE_HAMMING
import org.opencv.features2d.Features2d
import org.opencv.features2d.Features2d.NOT_DRAW_SINGLE_POINTS
import org.opencv.core.Scalar
import org.opencv.core.Mat






fun main() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    val orb = create()
    val matcher = DescriptorMatcher.create(BRUTEFORCE_HAMMING)
    val img1 = imread("C:\\Users\\raunol\\Desktop\\hht_statuscodes_sheets.png")
    val matOfKeyPoint1 = MatOfKeyPoint()
    val descriptor1 = Mat()
    orb.detect(img1, matOfKeyPoint1)
    orb.compute(img1, matOfKeyPoint1, descriptor1)


    val img2 = imread("C:\\Users\\raunol\\Desktop\\ms.jpg")
    val matOfKeyPoint2 = MatOfKeyPoint();
    val descriptor2 = Mat()
    orb.detect(img2, matOfKeyPoint2)
    orb.compute(img2, matOfKeyPoint2, descriptor2)

    val matches = MatOfDMatch()
    matcher.match(descriptor1, descriptor2, matches)
    val RED = Scalar(255.0, 0.0, 0.0)
    val GREEN = Scalar(0.0, 255.0, 0.0)
    //output image
    val outputImg = Mat()
    val drawnMatches = MatOfByte()
    //this will draw all matches, works fine
    Features2d.drawMatches(img1, matOfKeyPoint1, img2, matOfKeyPoint2, matches,
            outputImg, GREEN, RED, drawnMatches, NOT_DRAW_SINGLE_POINTS)
    var final_matches = mutableListOf<DMatch>()
    for (dMatch in matches.toArray()) {
        if (dMatch.distance <= 75) {
            final_matches.add(dMatch)
        }
    }
    print(final_matches.size)
}


