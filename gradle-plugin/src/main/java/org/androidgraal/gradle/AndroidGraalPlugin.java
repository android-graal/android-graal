package org.androidgraal.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AndroidGraalPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getLogger().lifecycle(
                "org.androidgraal.native-image is a placeholder; it does not configure anything yet.");
    }
}
